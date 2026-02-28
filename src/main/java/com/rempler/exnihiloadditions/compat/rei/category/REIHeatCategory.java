package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIHeatDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.ArrayList;
import java.util.List;

public class REIHeatCategory implements DisplayCategory<REIHeatDisplay> {

    @Override
    public CategoryIdentifier<? extends REIHeatDisplay> getCategoryIdentifier() {
        return EXAREIPlugin.HEAT;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("emi.category.exnihiloadditions.heating");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.FIRED_CRUCIBLE.itemStack());
    }

    @Override
    public int getDisplayWidth(REIHeatDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 40;
    }

    @Override
    public List<Widget> setupDisplay(REIHeatDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input block
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 5))
                .entries(display.getInputEntries().getFirst())
                .markInput());

        // Heat amount label
        widgets.add(Widgets.createLabel(
                new Point(startPoint.x + 30, startPoint.y + 10),
                Component.literal("Heat: " + display.getHeatAmount()).withStyle(ChatFormatting.DARK_RED)
        ).leftAligned());

        return widgets;
    }
}
