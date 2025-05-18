package com.rempler.exnihiloadditions.compat.rei.solidifying;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import com.rempler.exnihiloadditions.compat.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolidifyingRecipeDisplay extends SimpleBasicDisplay {
    public SolidifyingRecipeDisplay(SolidifyingRecipe recipe) {
        super(getIngredients(recipe), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.getResult()))));
    }

    private static List<EntryIngredient> getIngredients(SolidifyingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.of(recipe.getFluidOnTop().getFluid()));
        list.add(EntryIngredients.of(recipe.getFluidInTank().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return SolidifyingRecipeCategory.SOLIDIFY;
    }
}
