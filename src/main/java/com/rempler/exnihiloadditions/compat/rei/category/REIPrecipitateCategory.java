package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIPrecipitateDisplay;
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

public class REIPrecipitateCategory implements DisplayCategory<REIPrecipitateDisplay> {

    @Override
    public CategoryIdentifier<? extends REIPrecipitateDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.PRECIPITATE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.precipitating");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.ACACIA_BARREL.itemStack());
    }

    @Override
    public int getDisplayWidth(REIPrecipitateDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 50;
    }

    @Override
    public List<Widget> setupDisplay(REIPrecipitateDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.getCenterX() - 50, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in barrel
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 15))
                .entries(display.getInputEntries().get(1))
                .markInput());

        // Item input (catalyst dropped in)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 25, startPoint.y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 45, startPoint.y + 15)));

        // Output
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 15))
                .entries(display.getOutputEntries().getFirst())
                .markOutput());

        return widgets;
    }
}
