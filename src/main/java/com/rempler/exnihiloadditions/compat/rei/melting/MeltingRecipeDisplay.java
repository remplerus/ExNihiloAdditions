package com.rempler.exnihiloadditions.compat.rei.melting;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import com.rempler.exnihiloadditions.compat.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeltingRecipeDisplay extends SimpleBasicDisplay {
    public MeltingRecipeDisplay(MeltingRecipe recipe) {
        super(getIngredients(recipe), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.getResultFluid().getFluid()))));
    }

    private static List<EntryIngredient> getIngredients(MeltingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MeltingRecipeCategory.MELTING;
    }
}
