package com.rempler.exnihiloadditions.compat.rei.transition;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import com.rempler.exnihiloadditions.compat.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransitionRecipeDisplay extends SimpleBasicDisplay {
    public TransitionRecipeDisplay(TransitionRecipe recipe) {
        super(getIngredients(recipe), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.getResult().getFluid()))));
    }

    private static List<EntryIngredient> getIngredients(TransitionRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getCatalyst()));
        list.add(EntryIngredients.of(recipe.getFluidInTank().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TransitionRecipeCategory.TRANSITION;
    }
}
