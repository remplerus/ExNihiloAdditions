package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

import java.util.List;

public class REIPrecipitateDisplay extends BasicDisplay {
    private final PrecipitateRecipe recipe;

    public REIPrecipitateDisplay(PrecipitateRecipe recipe) {
        super(
                List.of(
                        EntryIngredients.ofIngredient(recipe.getInput()),
                        EntryIngredient.of(EntryStacks.of(
                                dev.architectury.fluid.FluidStack.create(
                                        recipe.getFluid().getFluid(),
                                        Config.getBarrelNumberOfBuckets() * 1000L
                                )
                        ))
                ),
                List.of(EntryIngredients.of(recipe.getOutput()))
        );
        this.recipe = recipe;
    }

    public PrecipitateRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.PRECIPITATE;
    }
}
