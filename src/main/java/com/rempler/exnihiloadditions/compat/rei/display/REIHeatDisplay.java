package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

import java.util.List;

public class REIHeatDisplay extends BasicDisplay {
    private final HeatRecipe recipe;

    public REIHeatDisplay(HeatRecipe recipe) {
        super(
                List.of(EntryIngredients.of(new ItemStack(recipe.getInputBlock()))),
                List.of() // Heat recipes show heat amount, no traditional output
        );
        this.recipe = recipe;
    }

    public HeatRecipe getRecipe() {
        return recipe;
    }

    public int getHeatAmount() {
        return recipe.getAmount();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.HEAT;
    }
}
