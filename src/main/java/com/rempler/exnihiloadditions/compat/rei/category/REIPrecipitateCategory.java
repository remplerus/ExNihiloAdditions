package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIPrecipitateDisplay;
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
        return RecipeLayoutConstants.PRECIPITATE_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.PRECIPITATE_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REIPrecipitateDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in barrel
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.PRECIPITATE_FLUID_X,
                sp.y + RecipeLayoutConstants.PRECIPITATE_FLUID_Y))
                .entries(display.getInputEntries().get(1))
                .markInput());

        // Item input (catalyst dropped in)
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.PRECIPITATE_ITEM_X,
                sp.y + RecipeLayoutConstants.PRECIPITATE_ITEM_Y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(
                sp.x + RecipeLayoutConstants.PRECIPITATE_ARROW_X,
                sp.y + RecipeLayoutConstants.PRECIPITATE_ARROW_Y)));

        // Output
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.PRECIPITATE_OUTPUT_X,
                sp.y + RecipeLayoutConstants.PRECIPITATE_OUTPUT_Y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());

        return widgets;
    }
}
