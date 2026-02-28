package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
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
        super(EXNEMIPlugin.PRECIPITATING, recipeHolder.id(), 70, 40);
        PrecipitateRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.inputs.add(EmiStack.of(recipe.getFluid().getFluid()).setAmount(Config.getBarrelNumberOfBuckets() * 1000L));
        this.outputs.add(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(1), 0, 20, 18, 18, 1000);
        widgetHolder.addTexture(PRECIPITATE_ARROW, 22, 19);
        widgetHolder.addSlot(inputs.get(0), 25, 0);
        widgetHolder.addSlot(outputs.getFirst(), 51, 20).recipeContext(this);
    }
}
