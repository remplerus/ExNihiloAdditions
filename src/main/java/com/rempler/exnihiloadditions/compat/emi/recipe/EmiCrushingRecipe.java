package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.novacore.util.StringUtils;

import java.util.Collection;

public class EmiCrushingRecipe extends BasicEmiRecipe {
    public EmiCrushingRecipe(CrushingRecipe recipe) {
        super(EXNEMIPlugin.SIFTING, recipe.getId(), 70, 18);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        Collection<EmiStack> outputs = recipe.getOutputsWithoutChance().stream()
                .map(EmiStack::of)
                .toList();
        this.outputs.addAll(outputs);
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(inputs.get(0), 11, 39);
        for (int i = 0; i < outputs.size(); i++) {
            int slotX = 39 + i % 7 * 18;
            int slotY = 3 + i / 7 * 18;
            widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this)
                .appendTooltip(Component.literal(String.format("%s", StringUtils.formatPercent(outputs.get(i).getChance()))));
        }
    }
}
