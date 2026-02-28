package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

import java.util.List;

public class REITransitionDisplay extends BasicDisplay {
    private final TransitionRecipe recipe;

    public REITransitionDisplay(TransitionRecipe recipe) {
        super(
                List.of(
                        EntryIngredient.of(EntryStacks.of(
                                dev.architectury.fluid.FluidStack.create(
                                        recipe.getFluidInTank().getFluid(),
                                        Config.getBarrelNumberOfBuckets() * 1000L
                                )
                        )),
                        EntryIngredients.ofIngredient(recipe.getCatalyst())
                ),
                List.of(EntryIngredient.of(EntryStacks.of(
                        dev.architectury.fluid.FluidStack.create(
                                recipe.getResult().getFluid(),
                                Config.getBarrelNumberOfBuckets() * 1000L
                        )
                )))
        );
        this.recipe = recipe;
    }

    public TransitionRecipe getRecipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.TRANSITION;
    }
}
