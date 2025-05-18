package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class EmiMeltingRecipe extends AbstractEmiRecipe {
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> outputs = new ArrayList<>();
    private final CrucibleBlockEntity.CrucibleType crucibleType;
    public EmiMeltingRecipe(MeltingRecipe recipe, CrucibleBlockEntity.CrucibleType crucibleType) {
        super(recipe);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(recipe.getResultFluid().getFluid()).setAmount(recipe.getResultFluid().getAmount()));
        this.crucibleType = crucibleType;
    }

    @Override
    public boolean supportsRecipeTree() {
        return true;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        if (crucibleType == CrucibleBlockEntity.CrucibleType.WOOD) {
            return EXNEMIPlugin.MELTING;
        } else {
            return EXNEMIPlugin.FIRED_MELTING;
        }
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
        return 70;
    }

    @Override
    public int getDisplayHeight() {
        return 20;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(inputs.get(0), 0, 0);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 19, 1);
        widgetHolder.addTank(outputs.get(0), 43, 0, 18, 18, 1000).recipeContext(this);
    }
}
