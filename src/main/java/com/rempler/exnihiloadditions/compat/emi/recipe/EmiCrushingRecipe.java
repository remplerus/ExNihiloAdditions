package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;

import java.util.ArrayList;
import java.util.List;

public class EmiCrushingRecipe extends AbstractEmiRecipe {
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> outputs = new ArrayList<>();
    private final int displayWidth;
    private final int displayHeight;

    public EmiCrushingRecipe(RecipeHolder<CrushingRecipe> recipeHolder) {
        super(recipeHolder.id());
        CrushingRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        for (ItemStackWithChance stack : recipe.getDrops()) {
            this.outputs.add(EmiStack.of(stack.getStack()).setChance(stack.getChance()));
        }
        this.displayWidth = RecipeLayoutConstants.gridDisplayWidth(outputs.size());
        this.displayHeight = RecipeLayoutConstants.gridDisplayHeight(outputs.size());
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
        return displayWidth;
    }

    @Override
    public int getDisplayHeight() {
        return displayHeight;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        int y = getDisplayHeight() - RecipeLayoutConstants.SLOT_SIZE;
        widgetHolder.addSlot(inputs.getFirst(), 0, y / 2);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, RecipeLayoutConstants.ARROW_OFFSET_X, y / 2 + 1);
        for (int i = 0; i < outputs.size(); i++) {
            int slotX = RecipeLayoutConstants.GRID_OUTPUT_START_X + (i % RecipeLayoutConstants.GRID_COLS) * RecipeLayoutConstants.SLOT_SIZE;
            int slotY = (i / RecipeLayoutConstants.GRID_COLS) * RecipeLayoutConstants.SLOT_SIZE;
            widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this);
        }
    }
}
