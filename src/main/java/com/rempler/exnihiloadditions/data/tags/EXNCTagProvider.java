package com.rempler.exnihiloadditions.data.tags;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.data.tags.TagProvider;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.concurrent.CompletableFuture;

public class EXNCTagProvider extends TagProvider {
    public EXNCTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExNihiloAdditions.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        if (ExNihiloAdditions.isTFCLoaded) {
            addToTag(ExNihiloTags.CRUCIBLE, EXNATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof CrucibleBlock).toArray(Block[]::new));
            addToTag(ExNihiloTags.BARREL, EXNATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof BarrelBlock).toArray(Block[]::new));
            addToTag(ExNihiloTags.SIEVE, EXNATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof SieveBlock).toArray(Block[]::new));
            addToTag(BlockTags.MINEABLE_WITH_AXE, EXNATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).toArray(Block[]::new));
        }
        addToTag(ExNihiloTags.CRUCIBLE, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof CrucibleBlock).toArray(Block[]::new));
        addToTag(ExNihiloTags.BARREL, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof BarrelBlock).toArray(Block[]::new));
        addToTag(ExNihiloTags.SIEVE, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof SieveBlock).toArray(Block[]::new));
    }

}
