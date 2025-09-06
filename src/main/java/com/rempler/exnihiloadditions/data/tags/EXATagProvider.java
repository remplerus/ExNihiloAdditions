package com.rempler.exnihiloadditions.data.tags;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.config.ModConfig;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.data.tags.TagProvider;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.concurrent.CompletableFuture;

public class EXATagProvider extends TagProvider {
    public EXATagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExNihiloAdditions.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        if (ExNihiloAdditions.isTFCLoaded) {
            addToTag(ExNihiloTags.CRUCIBLE, EXATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof CrucibleBlock).toArray(Block[]::new));
            addToTag(ExNihiloTags.BARREL, EXATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof BarrelBlock).toArray(Block[]::new));
            addToTag(ExNihiloTags.SIEVE, EXATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof SieveBlock).toArray(Block[]::new));
            addToTag(BlockTags.MINEABLE_WITH_AXE, EXATFCBlocks.getDefinitions().stream().map(BlockDefinition::block).toArray(Block[]::new));
        }
        addToTag(ExNihiloTags.CRUCIBLE, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof CrucibleBlock).toArray(Block[]::new));
        addToTag(ExNihiloTags.BARREL, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof BarrelBlock).toArray(Block[]::new));
        addToTag(ExNihiloTags.SIEVE, EXNBlocks.getDefinitions().stream().map(BlockDefinition::block).filter(block -> block instanceof SieveBlock).toArray(Block[]::new));

        if (ExNihiloAdditions.isBotaniaLoaded) {
            addToTag(ExNihiloTags.CROOK, EXABotaniaItems.getDefinitions().stream().map(ItemDefinition::asItem).filter(item -> item instanceof CrookItem).toArray(Item[]::new));
            addToTag(ExNihiloTags.HAMMER, EXABotaniaItems.getDefinitions().stream().map(ItemDefinition::asItem).filter(item -> item instanceof HammerItem).toArray(Item[]::new));
        }
    }

}
