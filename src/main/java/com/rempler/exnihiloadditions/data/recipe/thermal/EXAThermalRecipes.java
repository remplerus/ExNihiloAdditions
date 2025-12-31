package com.rempler.exnihiloadditions.data.recipe.thermal;

import cofh.thermal.core.ThermalCore;
import com.rempler.exnihiloadditions.compat.thermal.EXAThermalItems;
import com.rempler.exnihiloadditions.data.recipe.EXARecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.data.recipes.CrushingRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.data.recipes.SiftingRecipeBuilder;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;

import java.util.function.Consumer;

public class EXAThermalRecipes {

    public static void init(Consumer<FinishedRecipe> consumer) {
        registerCrafting(consumer);
        registerCrushing(consumer);
        registerSieve(consumer);
    }

    protected static void registerCrafting(Consumer<FinishedRecipe> consumer) {
        EXARecipeHelper.createDoll(consumer, EXAThermalItems.BASALZ_DOLL,
                Ingredient.of(EXAThermalTags.OBSIDIAN_DUST), Ingredient.of(Tags.Items.DUSTS_REDSTONE), Ingredient.of(Tags.Items.DUSTS_GLOWSTONE),
                Ingredient.of(EXNItems.CRAFTING_DOLL.asItem()), Ingredient.of(Tags.Items.CROPS_NETHER_WART), "thermal");
        EXARecipeHelper.createDoll(consumer, EXAThermalItems.BLIZZ_DOLL,
                Ingredient.of(Items.SNOWBALL), Ingredient.of(Tags.Items.DUSTS_REDSTONE), Ingredient.of(Tags.Items.DUSTS_GLOWSTONE),
                Ingredient.of(EXNItems.CRAFTING_DOLL), Ingredient.of(Tags.Items.CROPS_NETHER_WART), "thermal");
        EXARecipeHelper.createDoll(consumer, EXAThermalItems.BLITZ_DOLL,
                Ingredient.of(ThermalCore.ITEMS.get("niter_dust")), Ingredient.of(Tags.Items.DUSTS_REDSTONE), Ingredient.of(Tags.Items.DUSTS_GLOWSTONE),
                Ingredient.of(EXNItems.CRAFTING_DOLL.asItem()), Ingredient.of(Tags.Items.CROPS_NETHER_WART), "thermal");
    }

    protected static void registerCrushing(Consumer<FinishedRecipe> consumer) {
        CrushingRecipeBuilder.crushing(
                        Items.OBSIDIAN,
                        ItemStackWithChance.of(new ItemStack(EXAThermalItems.OBSIDIAN_DUST, 4)))
                .build(consumer, crushingLoc("dust_obsidian"));
    }

    private static void registerSieve(Consumer<FinishedRecipe> consumer) {
        SiftingRecipeBuilder.sifting(Ingredient.of(Tags.Items.GRAVEL), ThermalCore.ITEMS.get("niter"))
            .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
            .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
            .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
            .build(consumer, siftingLoc("niter"));

        SiftingRecipeBuilder.sifting(Ingredient.of(Tags.Items.GRAVEL), ThermalCore.ITEMS.get("sulfur"))
            .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
            .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
            .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
            .build(consumer, siftingLoc("sulfur"));

        SiftingRecipeBuilder.sifting(Ingredient.of(Tags.Items.GRAVEL), ThermalCore.ITEMS.get("apatite"))
            .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
            .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
            .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
            .build(consumer, siftingLoc("apatite"));

        SiftingRecipeBuilder.sifting(Ingredient.of(Tags.Items.GRAVEL), ThermalCore.ITEMS.get("cinnabar"))
            .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
            .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
            .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
            .build(consumer, siftingLoc("cinnabar"));
    }

    private static ResourceLocation siftingLoc(String id) {
        return new ResourceLocation(
                "exnihiloadditions",
                "thermal/sifting/" + RecipeProviderUtilities.prependRecipePrefix(id));
    }

    private static ResourceLocation crushingLoc(String id) {
        return new ResourceLocation(
                "exnihiloadditions",
                "thermal/crushing/" + RecipeProviderUtilities.prependRecipePrefix(id));
    }
}