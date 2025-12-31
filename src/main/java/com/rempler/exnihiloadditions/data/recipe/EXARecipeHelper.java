package com.rempler.exnihiloadditions.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.DollItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.function.Consumer;

public class EXARecipeHelper {
    public static void createCrook(Consumer<FinishedRecipe> consumer, ItemDefinition<CrookItem> result, Item input, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(recipe ->
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                                .pattern("xx")
                                .pattern(" x")
                                .pattern(" x")
                                .define('x', input)
                                .group("exnihilosequentia")
                                .unlockedBy("has_pebble", InventoryChangeTrigger.TriggerInstance.hasItems(input))
                                .save(recipe, new ResourceLocation(result.getId().getNamespace(), modid+"/crooks/"+result.getId().getPath()))
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(result.getId().getNamespace(), modid+"/crooks/"+result.getId().getPath()));
    }

    public static void createHammer(Consumer<FinishedRecipe> consumer, ItemDefinition<HammerItem> result, Item input, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(recipe ->
                        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                                .pattern(" x ")
                                .pattern(" sx")
                                .pattern("s  ")
                                .define('x', input)
                                .define('s', Tags.Items.RODS_WOODEN)
                                .group("exnihilosequentia")
                                .unlockedBy("has_pebble", InventoryChangeTrigger.TriggerInstance.hasItems(input))
                                .save(recipe, new ResourceLocation(result.getId().getNamespace(), modid+"/hammers/"+result.getId().getPath()))
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(result.getId().getNamespace(), modid+"/hammers/"+result.getId().getPath()));
    }

    public static void createBarrel(Consumer<FinishedRecipe> consumer, BlockDefinition<BarrelBlock> barrel, Item block, Item slab, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
            .addRecipe(recipe ->
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, barrel.block())
                    .pattern("x x")
                    .pattern("x x")
                    .pattern("x-x")
                    .define('x', block)
                    .define('-', slab)
                    .group("exnihilosequentia")
                    .unlockedBy("has_walls", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                    .unlockedBy("has_base", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
                    .save(recipe, new ResourceLocation(barrel.getId().getNamespace(), modid+"/barrels/"+barrel.getId().getPath()))
            )
            .generateAdvancement()
            .build(consumer, new ResourceLocation(barrel.getId().getNamespace(), modid+"/barrels/"+barrel.getId().getPath()));
    }

    public static void createCrucible(Consumer<FinishedRecipe> consumer, BlockDefinition<CrucibleBlock> crucible, Item block, Item slab, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
            .addRecipe(recipe ->
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crucible.block())
                    .pattern("c c")
                    .pattern("clc")
                    .pattern("s s")
                    .define('c', block)
                    .define('l', slab)
                    .define('s', Tags.Items.RODS_WOODEN)
                    .group("exnihilosequentia")
                    .unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                    .save(recipe, new ResourceLocation(crucible.getId().getNamespace(), modid+"/crucibles/"+crucible.getId().getPath()))
            )
            .generateAdvancement()
            .build(consumer, new ResourceLocation(crucible.getId().getNamespace(), modid+"/crucibles/"+crucible.getId().getPath()));
    }

    public static void createSieve(Consumer<FinishedRecipe> consumer, BlockDefinition<SieveBlock> sieve, Item block, Item slab, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(recipe ->
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sieve.block())
                                .pattern("p p")
                                .pattern("plp")
                                .pattern("s s")
                                .define('p', block)
                                .define('l', slab)
                                .define('s', Tags.Items.RODS_WOODEN)
                                .unlockedBy("has_plank", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                                .save(recipe, new ResourceLocation(sieve.getId().getNamespace(), modid+"/sieves/"+sieve.getId().getPath()))
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(sieve.getId().getNamespace(), modid+"/sieves/"+sieve.getId().getPath()));
    }

    public static void createDoll(Consumer<FinishedRecipe> consumer, ItemDefinition<DollItem> doll, Ingredient corner, Ingredient top, Ingredient notMiddle, Ingredient middle, Ingredient bottom, String modid) {
        ConditionalRecipe.builder().addCondition(modLoaded(modid))
                .addRecipe(recipe ->
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, doll)
                                .pattern("xvx")
                                .pattern("zyz")
                                .pattern("xwx")
                                .define('x', corner)
                                .define('v', top)
                                .define('z', notMiddle)
                                .define('y', middle)
                                .define('w', bottom)
                                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                                .unlockedBy("has_plank", InventoryChangeTrigger.TriggerInstance.hasItems(corner.getItems()[0].getItem()))
                                .save(recipe, new ResourceLocation(doll.getId().getNamespace(), modid+"/dolls/"+doll.getId().getPath()))
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(doll.getId().getNamespace(), modid+"/dolls/"+doll.getId().getPath()));
    }

    public static ICondition modLoaded(String modID) {
        return new ModLoadedCondition(modID);
    }
}
