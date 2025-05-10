package com.rempler.exnihiloadditions.data.recipe.tfc;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.data.recipe.EXNARecipeHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.function.Consumer;

public class EXNATFCRecipes {

    public static void init(Consumer<FinishedRecipe> consumer) {
        for (BlockDefinition<?> blockDefinition : EXNATFCBlocks.getDefinitions()) {
            String blockName1 = blockDefinition.getEnglishName().replace(" ", "_").toLowerCase();
            String blockName;
            if (blockName1.contains("_crucible")) {
                blockName = blockName1.replace("_crucible", "");
            } else if (blockName1.contains("_barrel")) {
                blockName = blockName1.replace("_barrel", "");
            } else if (blockName1.contains("_sieve")) {
                blockName = blockName1.replace("_sieve", "");
            } else {
                continue;
            }
            if (blockDefinition.block() instanceof CrucibleBlock) {
                BlockDefinition<CrucibleBlock> crucible = (BlockDefinition<CrucibleBlock>) blockDefinition;
                EXNARecipeHelper.createCrucible(consumer, crucible,
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/lumber/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/planks/"+blockName+"_slab")));
            } else if (blockDefinition.block() instanceof BarrelBlock) {
                BlockDefinition<BarrelBlock> barrel = (BlockDefinition<BarrelBlock>) blockDefinition;
                EXNARecipeHelper.createBarrel(consumer, barrel,
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/planks/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/planks/"+blockName+"_slab")));
            } else if (blockDefinition.block() instanceof SieveBlock) {
                BlockDefinition<SieveBlock> sieve = (BlockDefinition<SieveBlock>) blockDefinition;
                EXNARecipeHelper.createSieve(consumer, sieve,
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/planks/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation("tfc", "wood/planks/"+blockName+"_slab")));
            }
        }
    }
}
