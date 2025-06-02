package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

public class EmiSolidifyingRecipe extends BasicEmiRecipe {
    public EmiSolidifyingRecipe(SolidifyingRecipe recipe) {
        super(EXNEMIPlugin.SOLIDIFYING, EXNEMIPlugin.getPluginIdFromRecipe(recipe), 70, 40);
        this.inputs.add(EmiStack.of(recipe.getFluidInTank().getFluid()).setAmount(recipe.getFluidInTank().getAmount()));
        this.catalysts.add(EmiStack.of(recipe.getFluidOnTop().getFluid()).setAmount(recipe.getFluidOnTop().getAmount()));
        this.outputs.add(EmiStack.of(recipe.getResult()).setAmount(1L));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(0), 0, 20, 18, 18, 1000);
        widgetHolder.addTank(catalysts.get(0), 25, 0, 18, 18, 1000).catalyst(true);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 22, 20);
        widgetHolder.addSlot(outputs.get(0), 51, 20).recipeContext(this);
    }
}
