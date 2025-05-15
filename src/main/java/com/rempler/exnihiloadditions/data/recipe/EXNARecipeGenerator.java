package com.rempler.exnihiloadditions.data.recipe;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.data.recipe.tfc.EXNATFCRecipes;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.HeatRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.SiftingRecipeBuilder;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class EXNARecipeGenerator extends RecipeProvider {
    public EXNARecipeGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper, ExNihiloAdditions.MODID);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        if (ExNihiloAdditions.isTFCLoaded) {
            EXNATFCRecipes.init(consumer);
        }
        createEXNRecipes(consumer);
    }

    private void createEXNRecipes(Consumer<FinishedRecipe> consumer) {
        createHeatRecipes(consumer);
        createSiftingRecipes(consumer);
    }

    private void createSiftingRecipes(Consumer<FinishedRecipe> consumer) {
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_ANDESITE.block(), EXNItems.PEBBLE_ANDESITE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_BASALT.block(), EXNItems.PEBBLE_BASALT.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_BLACKSTONE.block(), EXNItems.PEBBLE_BLACKSTONE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_CALCITE.block(), EXNItems.PEBBLE_CALCITE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_DEEPSLATE.block(), EXNItems.PEBBLE_DEEPSLATE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_DIORITE.block(), EXNItems.PEBBLE_DIORITE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_DRIPSTONE.block(), EXNItems.PEBBLE_DRIPSTONE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_END_STONE.block(), EXNItems.PEBBLE_END_STONE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_GRANITE.block(), EXNItems.PEBBLE_GRANITE.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_NETHERRACK.block(), EXNItems.PEBBLE_NETHERRACK.asItem());
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_NETHERRACK.block(), EXNItems.PEBBLE_BASALT.asItem(), ExNihiloSequentia.makeId("sifting/ens_pebble_basalt"));
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_NETHERRACK.block(), EXNItems.PEBBLE_BLACKSTONE.asItem(), ExNihiloSequentia.makeId("sifting/ens_pebble_blackstone"));
        easySiftingRecipe(consumer, EXNBlocks.CRUSHED_TUFF.block(), EXNItems.PEBBLE_TUFF.asItem());
    }

    private void createHeatRecipes(Consumer<FinishedRecipe> consumer) {
        StatePropertiesPredicate lit = StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true).build();
        HeatRecipeBuilder.heat(Blocks.SOUL_TORCH, 1, lit)
                .build(consumer, ExNihiloAdditions.rl("heat/soul_torch"));
        HeatRecipeBuilder.heat(Blocks.SOUL_WALL_TORCH, 1, lit)
                .build(consumer, ExNihiloAdditions.rl("heat/soul_wall_torch"));
        HeatRecipeBuilder.heat(Blocks.LAVA_CAULDRON, 3)
                .build(consumer, ExNihiloAdditions.rl("heat/lava_cauldron"));
        HeatRecipeBuilder.heat(Blocks.BLAST_FURNACE, 3, lit)
                .build(consumer, ExNihiloAdditions.rl("heat/blast_furnace"));
        HeatRecipeBuilder.heat(Blocks.SMOKER, 3, lit)
                .build(consumer, ExNihiloAdditions.rl("heat/smoker"));
    }

    private void easySiftingRecipe(Consumer<FinishedRecipe> consumer, Block block, Item item, ResourceLocation rl) {
        SiftingRecipeBuilder.sifting(block, item)
                .addRoll(new MeshWithChance(MeshType.STRING, 0.1F))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.5F))
                .build(consumer, rl);
    }

    private void easySiftingRecipe(Consumer<FinishedRecipe> consumer, Block block, Item item) {
        easySiftingRecipe(consumer, block, item, ExNihiloAdditions.rl("sifting/"+block.getName().getString().replace("block.exnihilosequentia.", "")+"_to_"+item.getDescription().getString().replace("item.exnihilosequentia.", "")));
    }
}
