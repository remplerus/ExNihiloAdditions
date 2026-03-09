package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

public class EmiPrecipitateRecipe extends BasicEmiRecipe {
    private final EmiTexture PRECIPITATE_ARROW = new EmiTexture(EXNEMIPlugin.PRECIPITATING_SHEET, 71, 34, 24, 18);

    public EmiPrecipitateRecipe(RecipeHolder<PrecipitateRecipe> recipeHolder) {
        super(EXNEMIPlugin.PRECIPITATING, recipeHolder.id(),
                RecipeLayoutConstants.PRECIPITATE_WIDTH, RecipeLayoutConstants.PRECIPITATE_HEIGHT);
        PrecipitateRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.inputs.add(EmiStack.of(recipe.getFluid().getFluid())
                .setAmount(Config.getBarrelNumberOfBuckets() * 1000L));
        this.outputs.add(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(1),
                RecipeLayoutConstants.PRECIPITATE_FLUID_X, RecipeLayoutConstants.PRECIPITATE_FLUID_Y,
                RecipeLayoutConstants.TANK_WIDTH, RecipeLayoutConstants.TANK_HEIGHT,
                RecipeLayoutConstants.TANK_CAPACITY);
        widgetHolder.addTexture(PRECIPITATE_ARROW,
                RecipeLayoutConstants.PRECIPITATE_ARROW_X, RecipeLayoutConstants.PRECIPITATE_ARROW_Y);
        widgetHolder.addSlot(inputs.get(0),
                RecipeLayoutConstants.PRECIPITATE_ITEM_X, RecipeLayoutConstants.PRECIPITATE_ITEM_Y);
        widgetHolder.addSlot(outputs.getFirst(),
                RecipeLayoutConstants.PRECIPITATE_OUTPUT_X, RecipeLayoutConstants.PRECIPITATE_OUTPUT_Y)
                .recipeContext(this);
    }
}
