package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
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
        super(EXNEMIPlugin.COMPOSTING, recipeHolder.id(),
                RecipeLayoutConstants.COMPOST_WIDTH, RecipeLayoutConstants.COMPOST_HEIGHT);
        CompostRecipe recipe = recipeHolder.value();
        this.inputs.add(EmiIngredient.of(recipe.getInput())
                .setAmount(Config.getBarrelMaxSolidAmount() / recipe.getAmount()));
        this.outputs.add(EmiStack.of(Items.DIRT));
        this.solidAmount = recipe.getAmount();
    }

    @Override
    public boolean supportsRecipeTree() {
        return true;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(inputs.getFirst(), 0, 0)
                .appendTooltip(Component.literal(String.format("Amount: %d / %d",
                        solidAmount, Config.getBarrelMaxSolidAmount())));
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, RecipeLayoutConstants.ARROW_OFFSET_X, 1);
        widgetHolder.addSlot(outputs.getFirst(), RecipeLayoutConstants.OUTPUT_OFFSET_X, 0)
                .recipeContext(this);
    }
}
