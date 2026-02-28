package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REISolidifyingDisplay;
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

public class REISolidifyingCategory implements DisplayCategory<REISolidifyingDisplay> {

    @Override
    public CategoryIdentifier<? extends REISolidifyingDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.SOLIDIFYING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.solidifying");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.ACACIA_BARREL.itemStack());
    }

    @Override
    public int getDisplayWidth(REISolidifyingDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 50;
    }

    @Override
    public List<Widget> setupDisplay(REISolidifyingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.getCenterX() - 50, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in tank (bottom)
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 15))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Fluid on top (catalyst)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 25, startPoint.y))
                .entries(display.getInputEntries().get(1))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 45, startPoint.y + 15)));

        // Result
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 15))
                .entries(display.getOutputEntries().getFirst())
                .markOutput());

        return widgets;
    }
}
