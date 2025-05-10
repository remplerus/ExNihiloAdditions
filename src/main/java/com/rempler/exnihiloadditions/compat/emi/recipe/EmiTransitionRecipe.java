package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

public class EmiTransitionRecipe extends BasicEmiRecipe {
    public EmiTransitionRecipe(TransitionRecipe recipe) {
        super(EXNEMIPlugin.SIFTING, recipe.getId(), 70, 18);
        this.inputs.add(EmiStack.of(recipe.getFluidInTank().getFluid()));
        this.catalysts.add(EmiIngredient.of(recipe.getCatalyst()));
        this.outputs.add(EmiStack.of(recipe.getResult().getFluid()));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(0), 48, 10, 16, 16, 1000);
        widgetHolder.addSlot(catalysts.get(0), 75, 37);
        widgetHolder.addTank(outputs.get(0), 102, 10, 16, 16, 1000).recipeContext(this);
    }
}
