package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REISolidifyingDisplay;
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
        return RecipeLayoutConstants.SOLIDIFYING_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.SOLIDIFYING_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REISolidifyingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in tank (bottom)
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.SOLIDIFYING_TANK_X,
                sp.y + RecipeLayoutConstants.SOLIDIFYING_TANK_Y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Fluid on top (catalyst)
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.SOLIDIFYING_CATALYST_X,
                sp.y + RecipeLayoutConstants.SOLIDIFYING_CATALYST_Y))
                .entries(display.getInputEntries().get(1))
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(
                sp.x + RecipeLayoutConstants.SOLIDIFYING_ARROW_X,
                sp.y + RecipeLayoutConstants.SOLIDIFYING_ARROW_Y)));

        // Result item
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.SOLIDIFYING_OUTPUT_X,
                sp.y + RecipeLayoutConstants.SOLIDIFYING_OUTPUT_Y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());

        return widgets;
    }
}
