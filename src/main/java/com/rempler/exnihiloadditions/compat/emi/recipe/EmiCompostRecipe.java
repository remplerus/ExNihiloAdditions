package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;

public class EmiCompostRecipe extends BasicEmiRecipe {
    public EmiCompostRecipe(CompostRecipe recipe) {
        super(EXNEMIPlugin.COMPOSTING, recipe.getId(), 70, 18);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(Items.DIRT));
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        if (inputs.size() < 2) {
            widgetHolder.addSlot(inputs.get(0), 39, 3);
        } else {
            for(int i = 0; i < inputs.size(); ++i) {
                int slotX = 39 + i % 7 * 18;
                int slotY = 3 + i / 7 * 18;

                int solidAmount = ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount((ItemLike) inputs.get(i));
                widgetHolder.addSlot(inputs.get(i), slotX, slotY).appendTooltip(Component.literal(String.format("Amount: %d / %d", solidAmount, Config.getBarrelMaxSolidAmount())));
            }
        }
        widgetHolder.addSlot(outputs.get(0), 39, 39).recipeContext(this);
    }
}
