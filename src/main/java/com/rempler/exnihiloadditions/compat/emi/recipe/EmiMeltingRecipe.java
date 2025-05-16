package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

public class EmiMeltingRecipe extends BasicEmiRecipe {
    public static final EmiTexture MELTING_TEXTURE = new EmiTexture(EXNEMIPlugin.JEI_MID_SHEET, 0, 168, 166, 63);
    private int amount;

    public EmiMeltingRecipe(MeltingRecipe recipe) {
        super(EXNEMIPlugin.MELTING, recipe.getId(), 166, 63);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(recipe.getResultFluid().getFluid()).setAmount(recipe.getResultFluid().getAmount()));
        this.amount = recipe.getResultFluid().getAmount();
    }

    @Override
    public boolean supportsRecipeTree() {
        return true;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(outputs.get(0), 3, 21, 16, 16, 1000).recipeContext(this)
                .appendTooltip(Component.literal("Fluid Amount: " + amount + "mB"));
        for(int i = 0; i < inputs.size(); ++i) {
            int slotX = 39 + i % 7 * 18;
            int slotY = 3 + i / 7 * 18;
            widgetHolder.addSlot(inputs.get(i), slotX, slotY);
        }
    }
}
