package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIHarvestDisplay;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.item.EXNItems;

import java.util.ArrayList;
import java.util.List;

public class REIHarvestCategory implements DisplayCategory<REIHarvestDisplay> {
    @Override
    public CategoryIdentifier<? extends REIHarvestDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.HARVEST;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.harvesting");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNItems.CROOK_WOOD.itemStack());
    }

    @Override
    public int getDisplayWidth(REIHarvestDisplay display) {
        int outputCount = display.getOutputEntries().size();
        return RecipeLayoutConstants.gridDisplayWidth(outputCount) + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.gridDisplayHeight(7) + 10;
    }

    @Override
    public List<Widget> setupDisplay(REIHarvestDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        int outputCount = display.getOutputEntries().size();
        int contentHeight = RecipeLayoutConstants.gridDisplayHeight(outputCount);
        int y = contentHeight - RecipeLayoutConstants.SLOT_SIZE;

        widgets.add(Widgets.createSlot(new Point(sp.x, sp.y + y / 2))
                .entries(display.getInputEntries().get(0))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(sp.x + RecipeLayoutConstants.ARROW_OFFSET_X, sp.y + y / 2 + 1)));

        for (int i = 0; i < outputCount; i++) {
            int slotX = sp.x + RecipeLayoutConstants.GRID_OUTPUT_START_X + (i % RecipeLayoutConstants.GRID_COLS) * RecipeLayoutConstants.SLOT_SIZE;
            int slotY = sp.y + (i / RecipeLayoutConstants.GRID_COLS) * RecipeLayoutConstants.SLOT_SIZE;
            widgets.add(Widgets.createSlot(new Point(slotX, slotY))
                    .entries(display.getOutputEntries().get(i))
                    .markOutput());
        }

        return widgets;
    }
}
