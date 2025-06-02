package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.client.model.generators.AbstractBlockStateProvider;
import novamachina.novacore.world.level.block.BlockDefinition;

import javax.annotation.Nonnull;

public class EXNCBlockStateGenerator extends AbstractBlockStateProvider {

    public EXNCBlockStateGenerator(@Nonnull final PackOutput output, @Nonnull final ExistingFileHelper exFileHelper) {
        super(output, ExNihiloAdditions.MODID, exFileHelper);
    }

    protected void createCrucible(Block block, ResourceLocation texture, ResourceLocation particle) {
        ConfiguredModel model = new ConfiguredModel(this.models().getBuilder(this.getRegistryName(block)).parent(new ModelFile.UncheckedModelFile(this.exnihiloLoc("block/crucible_base"))).texture("particle", particle).texture("texture", texture).renderType("cutout_mipped"));
        this.simpleItemBlock(block, model.model);
    }

    protected void createBarrel(Block block, ResourceLocation texture, ResourceLocation particle) {
        ConfiguredModel model = new ConfiguredModel(this.models().getBuilder(this.getRegistryName(block)).parent(new ModelFile.UncheckedModelFile(this.exnihiloLoc("block/barrel_base"))).texture("texture", texture).texture("particle", particle).renderType("cutout_mipped"));
        this.simpleItemBlock(block, model.model);
    }

    protected void createSieve(Block block, ResourceLocation texture, ResourceLocation particle) {
        ConfiguredModel model = new ConfiguredModel(this.models().getBuilder(this.getRegistryName(block)).parent(new ModelFile.UncheckedModelFile(this.exnihiloLoc("block/sieve_base"))).texture("texture", texture).texture("particle", particle).renderType("cutout_mipped"));
        this.simpleItemBlock(block, model.model);
    }

    @Override
    protected void registerStatesAndModels() {
        if (ExNihiloAdditions.isTFCLoaded) {
            EXNATFCBlocks.getDefinitions().forEach(this::registerTFCBlocks);
        }
    }

    private void registerTFCBlocks(Object blockDefinition1) {
        if (blockDefinition1 instanceof BlockDefinition<?> blockDefinition) {
            ResourceLocation rl = ExNihiloAdditions.rl("block/tfc/" + blockDefinition.getId().getPath());
            registerBlock(blockDefinition, rl);
        }
    }

    private void registerBlock(BlockDefinition<?> blockDefinition, ResourceLocation rl) {
        if (blockDefinition.block() instanceof BarrelBlock) {
            createBarrel(blockDefinition.block(), rl, rl);
        } else if (blockDefinition.block() instanceof CrucibleBlock) {
            createCrucible(blockDefinition.block(), rl, rl);
        } else if (blockDefinition.block() instanceof SieveBlock) {
            createSieve(blockDefinition.block(), rl, rl);
        }
    }

    private ResourceLocation exnihiloLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, path);
    }
}
