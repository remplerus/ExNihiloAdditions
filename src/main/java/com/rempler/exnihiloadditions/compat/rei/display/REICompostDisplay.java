package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.Items;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;

import java.util.List;

public class REICompostDisplay extends BasicDisplay {
    private final CompostRecipe recipe;

    public REICompostDisplay(CompostRecipe recipe) {
        super(
                List.of(EntryIngredients.ofIngredient(recipe.getInput())),
                List.of(EntryIngredients.of(Items.DIRT))
        );
        this.recipe = recipe;
    }

    public CompostRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.COMPOST;
    }
}
