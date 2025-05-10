package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

public class EmiHeatRecipe extends BasicEmiRecipe {
    private int amount;
    public EmiHeatRecipe(HeatRecipe recipe) {
        super(EXNEMIPlugin.HEATING, recipe.getId(), 70, 18);
        this.inputs.add(EmiStack.of(recipe.getInputBlock()));
        this.amount = recipe.getAmount();
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(inputs.get(0), 0, 0).appendTooltip(Component.literal("Amount: " + amount + "x"));
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}
