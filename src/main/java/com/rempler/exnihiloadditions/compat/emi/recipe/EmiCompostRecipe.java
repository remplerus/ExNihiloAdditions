package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

public class EmiCompostRecipe extends BasicEmiRecipe {
    private final int solidAmount;
    public EmiCompostRecipe(CompostRecipe recipe) {
        super(EXNEMIPlugin.COMPOSTING, recipe.getId(), 140, 18);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(Items.DIRT));
        this.solidAmount = recipe.getAmount();
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        int x = 10;
        widgetHolder.addSlot(inputs.get(0), x, 0).appendTooltip(Component.literal(String.format("Amount: %d", solidAmount)));
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, x + 22, 1);
        widgetHolder.addSlot(EmiStack.of(EXNBlocks.ACACIA_BARREL.block()), x + 51, 0)
                .appendTooltip(Component.literal(String.format("Volume: %d", Config.getBarrelMaxSolidAmount())));
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, x + 73, 1);
        widgetHolder.addSlot(outputs.get(0), x + 102, 0).recipeContext(this);
    }
}
