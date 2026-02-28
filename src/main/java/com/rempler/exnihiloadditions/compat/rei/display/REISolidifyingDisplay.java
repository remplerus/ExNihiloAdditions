package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

import java.util.List;

public class REISolidifyingDisplay extends BasicDisplay {
    private final SolidifyingRecipe recipe;

    public REISolidifyingDisplay(SolidifyingRecipe recipe) {
        super(
                List.of(
                        EntryIngredient.of(EntryStacks.of(
                                dev.architectury.fluid.FluidStack.create(
                                        recipe.getFluidInTank().getFluid(),
                                        recipe.getFluidInTank().getAmount()
                                )
                        )),
                        EntryIngredient.of(EntryStacks.of(
                                dev.architectury.fluid.FluidStack.create(
                                        recipe.getFluidOnTop().getFluid(),
                                        recipe.getFluidOnTop().getAmount()
                                )
                        ))
                ),
                List.of(EntryIngredients.of(recipe.getResult()))
        );
        this.recipe = recipe;
    }

    public SolidifyingRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.SOLIDIFYING;
    }
}
