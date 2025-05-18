package com.rempler.exnihiloadditions.compat.rei.heat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import com.rempler.exnihiloadditions.compat.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

import java.util.Collections;

public class HeatRecipeDisplay extends SimpleBasicDisplay {
    public HeatRecipeDisplay(HeatRecipe recipe) {
        super(Collections.singletonList(EntryIngredients.of(recipe.getInputBlock())), Collections.emptyList());
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return HeatRecipeCategory.HEATING;
    }
}
