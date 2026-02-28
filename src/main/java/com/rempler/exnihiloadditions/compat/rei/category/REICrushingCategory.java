package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REICrushingDisplay;
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

public class REICrushingCategory implements DisplayCategory<REICrushingDisplay> {

    @Override
    public CategoryIdentifier<? extends REICrushingDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.CRUSHING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.crushing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNItems.HAMMER_WOOD.itemStack());
    }

    @Override
    public int getDisplayWidth(REICrushingDisplay display) {
        return 166;
    }

    @Override
    public int getDisplayHeight() {
        return 58;
    }

    @Override
    public List<Widget> setupDisplay(REICrushingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.x + 5, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input slot
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 5, startPoint.y + 19))
                .entries(display.getInputEntries().getFirst())
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 24, startPoint.y + 19)));

        // Output slots in a grid
        List<? extends List<?>> outputs = display.getOutputEntries();
        for (int i = 0; i < outputs.size() && i < 14; i++) {
            int slotX = startPoint.x + 55 + (i % 7 * 18);
            int slotY = startPoint.y + (i / 7 * 18);
            widgets.add(Widgets.createSlot(new Point(slotX, slotY))
                    .entries(display.getOutputEntries().get(i))
                    .markOutput());
        }

        return widgets;
    }
}
