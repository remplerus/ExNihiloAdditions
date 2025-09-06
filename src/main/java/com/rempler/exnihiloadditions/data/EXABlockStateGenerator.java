package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.data.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.level.block.BlockDefinition;

import javax.annotation.Nonnull;

public class EXABlockStateGenerator extends AbstractBlockStateGenerator {

    public EXABlockStateGenerator(@Nonnull final PackOutput output, @Nonnull final ExistingFileHelper exFileHelper) {
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
            EXATFCBlocks.getDefinitions().forEach(this::registerTFCBlocks);
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

}
