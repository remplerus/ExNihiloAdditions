package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

public class EmiSolidifyingRecipe extends BasicEmiRecipe {
    public EmiSolidifyingRecipe(SolidifyingRecipe recipe) {
        super(EXNEMIPlugin.SIFTING, recipe.getId(), 70, 18);
        this.inputs.add(EmiStack.of(recipe.getFluidInTank().getFluid()));
        this.catalysts.add(EmiStack.of(recipe.getFluidOnTop().getFluid()));
        this.outputs.add(EmiStack.of(recipe.getResult()));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(0), 48, 37, 16, 16, 1000);
        widgetHolder.addTank(catalysts.get(0), 75, 10, 16, 16, 1000);
        widgetHolder.addSlot(outputs.get(0), 102, 37).recipeContext(this);
    }
}
