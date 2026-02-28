package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;

import java.util.List;
import java.util.stream.Collectors;

public class REICrushingDisplay extends BasicDisplay {
    private final CrushingRecipe recipe;

    public REICrushingDisplay(CrushingRecipe recipe) {
        super(
                List.of(EntryIngredients.ofIngredient(recipe.getInput())),
                recipe.getOutputsWithoutChance().stream()
                        .map(EntryIngredients::of)
                        .collect(Collectors.toList())
        );
        this.recipe = recipe;
    }

    public CrushingRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.CRUSHING;
    }
}
