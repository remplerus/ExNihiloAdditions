package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REITransitionDisplay;
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

public class REITransitionCategory implements DisplayCategory<REITransitionDisplay> {

    @Override
    public CategoryIdentifier<? extends REITransitionDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.TRANSITION;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.transition");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.ACACIA_BARREL.itemStack());
    }

    @Override
    public int getDisplayWidth(REITransitionDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 50;
    }

    @Override
    public List<Widget> setupDisplay(REITransitionDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.getCenterX() - 50, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in tank
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 15))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Catalyst
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 25, startPoint.y))
                .entries(display.getInputEntries().get(1))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 45, startPoint.y + 15)));

        // Result fluid
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 15))
                .entries(display.getOutputEntries().getFirst())
                .markOutput());

        return widgets;
    }
}
