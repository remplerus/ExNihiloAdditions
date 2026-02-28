package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;

public class EmiCompostRecipe extends BasicEmiRecipe {
    private final int solidAmount;
    public EmiCompostRecipe(RecipeHolder<CompostRecipe> recipeHolder) {
        super(EXNEMIPlugin.COMPOSTING, recipeHolder.id(), 70, 18);
        CompostRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput()).setAmount(Config.getBarrelMaxSolidAmount() / recipe.getAmount()));
        this.outputs.add(EmiStack.of(Items.DIRT));
        this.solidAmount = recipe.getAmount();
    }

    @Override
    public boolean supportsRecipeTree() {
        return true;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        int x = 0;
        widgetHolder.addSlot(inputs.getFirst(), x, 0).appendTooltip(Component.literal(String.format("Amount: %d / %d", solidAmount, Config.getBarrelMaxSolidAmount())));
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, x + 19, 1);
        widgetHolder.addSlot(outputs.getFirst(), x + 43, 0).recipeContext(this);
    }
}
