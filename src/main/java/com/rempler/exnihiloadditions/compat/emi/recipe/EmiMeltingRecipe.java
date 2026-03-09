package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class EmiMeltingRecipe extends AbstractEmiRecipe {
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> outputs = new ArrayList<>();
    private final CrucibleBlockEntity.CrucibleType crucibleType;

    public EmiMeltingRecipe(RecipeHolder<MeltingRecipe> recipeHolder) {
        super(recipeHolder.id());
        MeltingRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(recipe.getResultFluid().getFluid())
                .setAmount(recipe.getResultFluid().getAmount()));
        this.crucibleType = recipe.getCrucibleType();
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
        return RecipeLayoutConstants.MELTING_WIDTH;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.MELTING_HEIGHT;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(inputs.getFirst(), 0, 0);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, RecipeLayoutConstants.ARROW_OFFSET_X, 1);
        widgetHolder.addTank(outputs.getFirst(), RecipeLayoutConstants.OUTPUT_OFFSET_X, 0,
                RecipeLayoutConstants.TANK_WIDTH, RecipeLayoutConstants.TANK_HEIGHT,
                RecipeLayoutConstants.TANK_CAPACITY).recipeContext(this);
    }
}
