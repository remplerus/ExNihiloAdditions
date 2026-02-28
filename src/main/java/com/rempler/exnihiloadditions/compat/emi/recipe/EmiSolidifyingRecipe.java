package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

public class EmiSolidifyingRecipe extends BasicEmiRecipe {
    public EmiSolidifyingRecipe(RecipeHolder<SolidifyingRecipe> recipeHolder) {
        super(EXNEMIPlugin.SOLIDIFYING, recipeHolder.id(),
                RecipeLayoutConstants.SOLIDIFYING_WIDTH, RecipeLayoutConstants.SOLIDIFYING_HEIGHT);
        SolidifyingRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiStack.of(recipe.getFluidInTank().getFluid())
                .setAmount(recipe.getFluidInTank().getAmount()));
        this.catalysts.add(EmiStack.of(recipe.getFluidOnTop().getFluid())
                .setAmount(recipe.getFluidOnTop().getAmount()));
        this.outputs.add(EmiStack.of(recipe.getResult()).setAmount(1L));
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.getFirst(),
                RecipeLayoutConstants.SOLIDIFYING_TANK_X, RecipeLayoutConstants.SOLIDIFYING_TANK_Y,
                RecipeLayoutConstants.TANK_WIDTH, RecipeLayoutConstants.TANK_HEIGHT,
                RecipeLayoutConstants.TANK_CAPACITY);
        widgetHolder.addTank(catalysts.getFirst(),
                RecipeLayoutConstants.SOLIDIFYING_CATALYST_X, RecipeLayoutConstants.SOLIDIFYING_CATALYST_Y,
                RecipeLayoutConstants.TANK_WIDTH, RecipeLayoutConstants.TANK_HEIGHT,
                RecipeLayoutConstants.TANK_CAPACITY).catalyst(true);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW,
                RecipeLayoutConstants.SOLIDIFYING_ARROW_X, RecipeLayoutConstants.SOLIDIFYING_ARROW_Y);
        widgetHolder.addSlot(outputs.getFirst(),
                RecipeLayoutConstants.SOLIDIFYING_OUTPUT_X, RecipeLayoutConstants.SOLIDIFYING_OUTPUT_Y)
                .recipeContext(this);
    }
}
