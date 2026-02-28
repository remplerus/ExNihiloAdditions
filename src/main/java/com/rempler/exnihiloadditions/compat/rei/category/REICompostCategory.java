package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REICompostDisplay;
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

public class REICompostCategory implements DisplayCategory<REICompostDisplay> {

    @Override
    public CategoryIdentifier<? extends REICompostDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.COMPOST;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.composting");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.ACACIA_BARREL.itemStack());
    }

    @Override
    public int getDisplayWidth(REICompostDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }

    @Override
    public List<Widget> setupDisplay(REICompostDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 24, startPoint.y + 1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 1))
                .entries(display.getInputEntries().getFirst())
                .markInput());
        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y + 1))
                    .entries(display.getOutputEntries().getFirst())
                    .markOutput());
        }

        return widgets;
    }
}
