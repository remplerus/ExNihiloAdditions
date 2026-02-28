package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIMeltingDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.ArrayList;
import java.util.List;

public class REIMeltingCategory implements DisplayCategory<REIMeltingDisplay> {
    private final boolean isFired;

    public REIMeltingCategory(boolean isFired) {
        this.isFired = isFired;
    }

    @Override
    public CategoryIdentifier<? extends REIMeltingDisplay> getCategoryIdentifier() {
        return isFired ? EXAREIPlugin.FIRED_MELTING : EXAREIPlugin.MELTING;
    }

    @Override
    public Component getTitle() {
        return isFired
                ? Component.translatable("emi.category.exnihiloadditions.fired_melting")
                : Component.translatable("emi.category.exnihiloadditions.melting");
    }

    @Override
    public Renderer getIcon() {
        return isFired
                ? EntryStacks.of(EXNBlocks.FIRED_CRUCIBLE.itemStack())
                : EntryStacks.of(EXNBlocks.ACACIA_CRUCIBLE.itemStack());
    }

    @Override
    public int getDisplayWidth(REIMeltingDisplay display) {
        return 166;
    }

    @Override
    public int getDisplayHeight() {
        return 58;
    }

    @Override
    public List<Widget> setupDisplay(REIMeltingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.x + 5, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input items in a grid (up to 21 items per display)
        List<? extends List<?>> inputs = display.getInputEntries();
        for (int i = 0; i < inputs.size() && i < 21; i++) {
            int slotX = startPoint.x + (i % 7 * 18);
            int slotY = startPoint.y + (i / 7 * 18);
            widgets.add(Widgets.createSlot(new Point(slotX, slotY))
                    .entries(display.getInputEntries().get(i))
                    .markInput());
        }

        // Arrow
        int arrowY = startPoint.y + 19;
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 128, arrowY)));

        // Fluid output
        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 140, startPoint.y + 19))
                    .entries(display.getOutputEntries().getFirst())
                    .markOutput());
        }

        return widgets;
    }
}
