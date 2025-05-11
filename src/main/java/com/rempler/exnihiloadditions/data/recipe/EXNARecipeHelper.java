package com.rempler.exnihiloadditions.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.level.block.BlockDefinition;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public class EXNARecipeHelper {
    public static void createCrook(Consumer<FinishedRecipe> consumer, Item result, Item input, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group("exnihilosequentia")
                .unlockedBy("has_pebble", InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, new ResourceLocation(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result)).getNamespace(),
                        modid+"/crooks/"+Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result)).getPath()));
    }

    public static void createBarrel(Consumer<FinishedRecipe> consumer, BlockDefinition<BarrelBlock> barrel, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, barrel.block())
                .pattern("x x")
                .pattern("x x")
                .pattern("x-x")
                .define('x', block)
                .define('-', slab)
                .group("exnihilosequentia")
                .unlockedBy("has_walls", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                .unlockedBy("has_base", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
                .save(consumer, new ResourceLocation(barrel.getId().getNamespace(), modid+"/barrels/"+barrel.getId().getPath()));
    }

    public static void createCrucible(Consumer<FinishedRecipe> consumer, BlockDefinition<CrucibleBlock> crucible, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crucible.block())
                .pattern("c c")
                .pattern("clc")
                .pattern("s s")
                .define('c', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .group("exnihilosequentia")
                .unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                .save(consumer, new ResourceLocation(crucible.getId().getNamespace(), modid+"/crucibles/"+crucible.getId().getPath()));
    }

    public static void createSieve(Consumer<FinishedRecipe> consumer, BlockDefinition<SieveBlock> sieve, Item block, Item slab, String modid) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sieve.block())
                .pattern("p p")
                .pattern("plp")
                .pattern("s s")
                .define('p', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_plank", InventoryChangeTrigger.TriggerInstance.hasItems(block))
                .save(consumer, new ResourceLocation(sieve.getId().getNamespace(), modid+"/sieves/"+sieve.getId().getPath()));
    }
}
