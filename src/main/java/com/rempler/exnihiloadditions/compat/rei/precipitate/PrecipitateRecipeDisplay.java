package com.rempler.exnihiloadditions.compat.rei.precipitate;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import com.rempler.exnihiloadditions.compat.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrecipitateRecipeDisplay extends SimpleBasicDisplay {
    public PrecipitateRecipeDisplay(PrecipitateRecipe recipe) {
        super(getIngredients(recipe), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.getOutput()))));
    }

    private static List<EntryIngredient> getIngredients(PrecipitateRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        list.add(EntryIngredients.of(recipe.getFluid().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PrecipitateRecipeCategory.PRECIPITATE;
    }
}
