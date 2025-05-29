package com.rempler.exnihiloadditions.api.registries;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.registers.EXABlocks;
import com.rempler.exnihiloadditions.registers.EXAItems;
import com.rempler.exnihiloadditions.api.EXAJsonParser;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.data.loading.DatagenModLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class EXARegistry<I extends AbstractItemLike> implements Iterable<I> {
    private final List<I> values = new ArrayList<>();
    private final String configFolder;
    private final String suffix;

    public EXARegistry(String configFolder) {
        this(configFolder, configFolder);
    }

    public EXARegistry(String configFolder, String suffix) {
        this.configFolder = configFolder;
        this.suffix = suffix;

        Preconditions.checkArgument(!suffix.startsWith("_"));
    }

    public void search(Function<EXAJsonParser, I> registrar) {
        Path configPath = Paths.get("config/"+ ExNihiloAdditions.ModIds.MODID + "/"+configFolder+"/materials/");

        if (!DatagenModLoader.isRunningDataGen()) {
            if (createConfigFolder(configPath)) {
                File configFile = configPath.toFile();
                String[] files = configFile.list();
                if (files != null) {
                    for (String file : files) {
                        if (file.endsWith(".json")) {
                            Path path = configPath.resolve(file);
                            try {
                                JsonObject json = (JsonObject) JsonParser.parseString(Files.readString(path));
                                I parser = registrar.apply(new EXAJsonParser(json, path, this));
                                if (parser != null) {
                                    register(file.substring(0, file.length() - 5), parser, parser.requiredModId);
                                }
                            } catch (IOException e) {
                                ExNihiloAdditions.LOGGER.error("Failed to read file: {}", path);
                            }
                        }
                    }
                } else {
                    ExNihiloAdditions.LOGGER.error("Failed to list files in directory: {}", configPath);
                }
            }
        }
    }

    public void register(String name, I item, String modid) {
        String id = modid + "_" + name + "_" + suffix;

        if (item.block != null) {
            throw new IllegalStateException(configFolder + "item with name " + name + " already registered: duplicate item");
        }

        item.block = EXABlocks.BLOCKS.register(id, item::createBlock);
        item.item = EXAItems.ITEMS.register(id, () -> item.createBlockItem(item.block.get()));
        values.add(item);
    }

    public <B extends BlockEntity> BlockEntityType<B> createBlockEntityType(BlockEntityType.BlockEntitySupplier<? extends B> factory) {
        ImmutableSet.Builder<Block> validBlocks = ImmutableSet.builder();

        for (var material : this.values) {
            validBlocks.add(material.block.get());
        }

        return new BlockEntityType<>(factory, validBlocks.build(), null);
    }

    @Override
    public @NotNull Iterator<I> iterator() {
        return values.iterator();
    }

    public Stream<I> stream() {
        return values.stream();
    }

    private boolean createConfigFolder(Path path) {
        File configFolder = path.getParent().toFile();
        File realFolder = path.toFile();
        return (configFolder.exists() || configFolder.mkdirs()) && (realFolder.exists() || realFolder.mkdirs()) && configFolder.isDirectory();
    }
}
