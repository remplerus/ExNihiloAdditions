package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

public class EmiPrecipitateRecipe extends BasicEmiRecipe {
    public EmiPrecipitateRecipe(PrecipitateRecipe recipe) {
        super(EXNEMIPlugin.SIFTING, recipe.getId(), 70, 18);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.catalysts.add(EmiStack.of(recipe.getFluid().getFluid()));
        this.outputs.add(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(catalysts.get(0), 48, 37, 16, 16, 1000);
        widgetHolder.addSlot(inputs.get(0), 75, 10);
        widgetHolder.addSlot(outputs.get(0), 102, 37).recipeContext(this);
    }
}
