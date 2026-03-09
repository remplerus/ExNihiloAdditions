package com.rempler.exnihiloadditions.data.recipe;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.data.recipe.tfc.EXNATFCRecipes;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.HeatRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.SiftingRecipeBuilder;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class EXNARecipeGenerator extends RecipeProvider {
    public EXNARecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        if (ExNihiloAdditions.isTFCLoaded) {
            //EXNATFCRecipes.init(consumer);
        }
        createEXNRecipes(consumer);
    }

    private void createEXNRecipes(RecipeOutput consumer) {
        createHeatRecipes(consumer);
        createSiftingRecipes(consumer);
    }

    private void createSiftingRecipes(RecipeOutput consumer) {
        //Added in ExNihilo 1.21.1
    }

    private void createHeatRecipes(RecipeOutput consumer) {
        //Added to ExNihilo 1.21.1
    }

    private void easySiftingRecipe(RecipeOutput consumer, Block block, Item item, ResourceLocation rl, boolean rich) {
        if (rich) {
            SiftingRecipeBuilder.sifting(block, item)
                    .addRoll(new MeshWithChance(MeshType.STRING, 1F))
                    .addRoll(new MeshWithChance(MeshType.STRING, 1F))
                    .addRoll(new MeshWithChance(MeshType.STRING, 0.5F))
                    .addRoll(new MeshWithChance(MeshType.STRING, 0.1F))
                    .build(consumer, rl);
        } else {
            SiftingRecipeBuilder.sifting(block, item)
                    .addRoll(new MeshWithChance(MeshType.STRING, 0.5F))
                    .addRoll(new MeshWithChance(MeshType.STRING, 0.1F))
                    .build(consumer, rl);
        }
    }

    private void easySiftingRecipe(RecipeOutput consumer, Block block, Item item, boolean rich) {
        easySiftingRecipe(consumer, block, item, ExNihiloAdditions.rl("sifting/"+block.getName().getString().replace("block.exnihilosequentia.", "")+"_to_"+item.getDescription().getString().replace("item.exnihilosequentia.", "")), rich);
    }
}
