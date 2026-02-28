package com.rempler.exnihiloadditions.compat.rei;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.rei.category.*;
import com.rempler.exnihiloadditions.compat.rei.display.*;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.ModList;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.*;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;

import java.util.*;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@REIPluginClient
public class EXAREIPlugin implements REIClientPlugin {
    private static final Logger log = LoggerFactory.getLogger(EXAREIPlugin.class);

    // Category Identifiers
    public static final CategoryIdentifier<REICompostDisplay> COMPOST =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_compost"));
    public static final CategoryIdentifier<REICrushingDisplay> CRUSHING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_crushing"));
    public static final CategoryIdentifier<REIHarvestDisplay> HARVEST =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_harvest"));
    public static final CategoryIdentifier<REIHeatDisplay> HEAT =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_heat"));
    public static final CategoryIdentifier<REIMeltingDisplay> MELTING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_melting"));
    public static final CategoryIdentifier<REIMeltingDisplay> FIRED_MELTING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_fired_melting"));
    public static final CategoryIdentifier<REIPrecipitateDisplay> PRECIPITATE =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_precipitate"));
    public static final CategoryIdentifier<REISiftingDisplay> DRY_SIFTING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_dry_sifting"));
    public static final CategoryIdentifier<REISiftingDisplay> WET_SIFTING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_wet_sifting"));
    public static final CategoryIdentifier<REISolidifyingDisplay> SOLIDIFYING =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_solidifying"));
    public static final CategoryIdentifier<REITransitionDisplay> TRANSITION =
            CategoryIdentifier.of(ExNihiloAdditions.rl("rei_transition"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        if (!shouldRegister()) return;

        registry.add(new REICompostCategory());
        registry.add(new REICrushingCategory());
        registry.add(new REIHarvestCategory());
        registry.add(new REIHeatCategory());
        registry.add(new REIMeltingCategory(false)); // Wood crucible
        registry.add(new REIMeltingCategory(true));  // Fired crucible
        registry.add(new REIPrecipitateCategory());
        registry.add(new REISiftingCategory(false)); // Dry
        registry.add(new REISiftingCategory(true));  // Wet
        registry.add(new REISolidifyingCategory());
        registry.add(new REITransitionCategory());

        // Register workstations
        registerWorkstations(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        if (!shouldRegister()) return;

        // Compost
        for (CompostRecipe recipe : ExNihiloRegistries.COMPOST_REGISTRY.getRecipeList()) {
            registry.add(new REICompostDisplay(recipe));
        }

        // Crushing
        for (CrushingRecipe recipe : ExNihiloRegistries.HAMMER_REGISTRY.getRecipeList()) {
            registry.add(new REICrushingDisplay(recipe));
        }

        // Harvest
        for (HarvestRecipe recipe : ExNihiloRegistries.CROOK_REGISTRY.getRecipeList()) {
            registry.add(new REIHarvestDisplay(recipe));
        }

        // Heat
        for (HeatRecipe recipe : ExNihiloRegistries.HEAT_REGISTRY.getRecipeList()) {
            registry.add(new REIHeatDisplay(recipe));
        }

        // Melting (crucible recipes)
        List<MeltingRecipe> meltingRecipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList();
        for (MeltingRecipe recipe : meltingRecipes) {
            if (recipe.getInput().getItems().length <= 21) {
                registry.add(new REIMeltingDisplay(recipe, Arrays.asList(recipe.getInput().getItems())));
            } else {
                List<List<ItemStack>> partitions = Lists.partition(List.of(recipe.getInput().getItems()), 21);
                for (List<ItemStack> partition : partitions) {
                    registry.add(new REIMeltingDisplay(recipe, partition));
                }
            }
        }

        // Precipitate
        for (PrecipitateRecipe recipe : ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getRecipeList()) {
            registry.add(new REIPrecipitateDisplay(recipe));
        }

        // Sifting
        List<SiftingRecipe> siftingRecipes = ExNihiloRegistries.SIEVE_REGISTRY.getRecipeList();
        Map<String, List<SiftingRecipe>> groupedRecipes = new HashMap<>();
        for (SiftingRecipe recipe : siftingRecipes) {
            String key = recipe.getInput().toString() + "_" + recipe.isWaterlogged();
            groupedRecipes.computeIfAbsent(key, k -> new ArrayList<>()).add(recipe);
        }
        for (List<SiftingRecipe> recipes : groupedRecipes.values()) {
            SiftingRecipe baseRecipe = recipes.getFirst();
            registry.add(new REISiftingDisplay(baseRecipe));
        }

        // Solidifying
        for (SolidifyingRecipe recipe : ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getRecipeList()) {
            registry.add(new REISolidifyingDisplay(recipe));
        }

        // Transition
        for (TransitionRecipe recipe : ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getRecipeList()) {
            registry.add(new REITransitionDisplay(recipe));
        }

        log.info("REI displays registered successfully");
    }

    private void registerWorkstations(CategoryRegistry registry) {
        // Barrels
        for (ItemStack stack : getTagItems(ExNihiloTags.BARREL)) {
            registry.addWorkstations(COMPOST, EntryStacks.of(stack));
            registry.addWorkstations(PRECIPITATE, EntryStacks.of(stack));
            registry.addWorkstations(SOLIDIFYING, EntryStacks.of(stack));
            registry.addWorkstations(TRANSITION, EntryStacks.of(stack));
        }

        // Crucibles
        for (ItemStack stack : getTagItems(ExNihiloTags.CRUCIBLE)) {
            registry.addWorkstations(HEAT, EntryStacks.of(stack));
            registry.addWorkstations(MELTING, EntryStacks.of(stack));
        }
        registry.addWorkstations(FIRED_MELTING, EntryStacks.of(EXNBlocks.FIRED_CRUCIBLE.itemStack()));
        registry.addWorkstations(FIRED_MELTING, EntryStacks.of(EXNBlocks.CRIMSON_CRUCIBLE.itemStack()));
        registry.addWorkstations(FIRED_MELTING, EntryStacks.of(EXNBlocks.WARPED_CRUCIBLE.itemStack()));

        // Sieves
        for (ItemStack stack : getTagItems(ExNihiloTags.SIEVE)) {
            registry.addWorkstations(DRY_SIFTING, EntryStacks.of(stack));
            registry.addWorkstations(WET_SIFTING, EntryStacks.of(stack));
        }

        // Crooks
        for (ItemStack stack : getTagItems(ExNihiloTags.CROOK)) {
            registry.addWorkstations(HARVEST, EntryStacks.of(stack));
        }

        // Hammers
        for (ItemStack stack : getTagItems(ExNihiloTags.HAMMER)) {
            registry.addWorkstations(CRUSHING, EntryStacks.of(stack));
        }
    }

    private static List<ItemStack> getTagItems(TagKey<Item> tag) {
        List<ItemStack> items = new ArrayList<>();
        BuiltInRegistries.ITEM.getTag(tag).ifPresent(holders -> {
            for (Holder<Item> holder : holders) {
                items.add(new ItemStack(holder.value()));
            }
        });
        return items;
    }

    /**
     * Only register REI if REI is loaded and EMI is NOT loaded (EMI has its own JEI compat layer).
     */
    private boolean shouldRegister() {
        return ModList.get().isLoaded("roughlyenoughitems") && !ModList.get().isLoaded("emi");
    }
}
