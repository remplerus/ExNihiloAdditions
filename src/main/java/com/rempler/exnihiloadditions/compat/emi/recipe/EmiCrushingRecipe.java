package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;

import java.util.ArrayList;
import java.util.List;

public class EmiCrushingRecipe extends AbstractEmiRecipe {
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> outputs = new ArrayList<>();
    private final int x;
    private final int y;

    public EmiCrushingRecipe(CrushingRecipe recipe) {
        super(recipe);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        for (ItemStackWithChance stack : recipe.getDrops()) {
            this.outputs.add(EmiStack.of(stack.getStack()).setChance(stack.getChance()));
        }
        int x = 187;
        switch (recipe.getDrops().size()) {
            case 1 -> x = x - 7*18;
            case 2 -> x = x - 6*18;
            case 3 -> x = x - 5*18;
            case 4 -> x = x - 4*18;
            case 5 -> x = x - 3*18;
            case 6 -> x = x - 2*18;
            case 7 -> x = x - 18;
        }
        this.x = x;
        int y = 18;
        if (outputs.size() > 7) {
            if (outputs.size() <= 14) {
                y = y + 18;
            } else if (outputs.size() <= 21) {
                y = y + 2*18;
            } else if (outputs.size() <= 28) {
                y = y + 3*18;
            }
        }
        this.y = y;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EXNEMIPlugin.CRUSHING;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return x;
    }

    @Override
    public int getDisplayHeight() {
        return y;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        int y = getDisplayHeight() - 18;
        widgetHolder.addSlot(inputs.get(0), 0, y/2);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 19, y/2+1);
        for (int i = 0; i < outputs.size(); i++) {
            int slotX = 43 + i % 7 * 18;
            int slotY = i / 7 * 18;
            widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this);
        }
    }
}
