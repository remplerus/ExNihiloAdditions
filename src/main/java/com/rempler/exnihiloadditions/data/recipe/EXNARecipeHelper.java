package com.rempler.exnihiloadditions.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.Objects;

public class EXNARecipeHelper {
    public static void createCrook(RecipeOutput consumer, Item result, Item input, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
            .pattern("xx")
            .pattern(" x")
            .pattern(" x")
            .define('x', input)
            .group("exnihilosequentia")
            .unlockedBy("has_pebble", InventoryChangeTrigger.TriggerInstance.hasItems(input))
            .save(consumer.withConditions(modLoaded(modid)), ResourceLocation.fromNamespaceAndPath(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result)).getNamespace(),
                modid+"/crooks/"+Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result)).getPath()));
    }

    public static void createBarrel(RecipeOutput consumer, BlockDefinition<BarrelBlock> barrel, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, barrel.block())
            .pattern("x x")
            .pattern("x x")
            .pattern("x-x")
            .define('x', block)
            .define('-', slab)
            .group("exnihilosequentia")
            .unlockedBy("has_walls", InventoryChangeTrigger.TriggerInstance.hasItems(block))
            .unlockedBy("has_base", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
            .save(consumer.withConditions(modLoaded(modid)), ResourceLocation.fromNamespaceAndPath(barrel.getId().getNamespace(), modid+"/barrels/"+barrel.getId().getPath()));
    }

    public static void createCrucible(RecipeOutput consumer, BlockDefinition<CrucibleBlock> crucible, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crucible.block())
            .pattern("c c")
            .pattern("clc")
            .pattern("s s")
            .define('c', block)
            .define('l', slab)
            .define('s', Tags.Items.RODS_WOODEN)
            .group("exnihilosequentia")
            .unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(block))
            .save(consumer.withConditions(modLoaded(modid)), ResourceLocation.fromNamespaceAndPath(crucible.getId().getNamespace(), modid+"/crucibles/"+crucible.getId().getPath()));
    }

    public static void createSieve(RecipeOutput consumer, BlockDefinition<SieveBlock> sieve, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sieve.block())
            .pattern("p p")
            .pattern("plp")
            .pattern("s s")
            .define('p', block)
            .define('l', slab)
            .define('s', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_plank", InventoryChangeTrigger.TriggerInstance.hasItems(block))
            .save(consumer.withConditions(modLoaded(modid)), ResourceLocation.fromNamespaceAndPath(sieve.getId().getNamespace(), modid+"/sieves/"+sieve.getId().getPath()));
    }

    public static ICondition modLoaded(String modID) {
        return new ModLoadedCondition(modID);
    }
}
