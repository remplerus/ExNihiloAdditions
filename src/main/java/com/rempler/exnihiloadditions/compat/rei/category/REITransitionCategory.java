package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REITransitionDisplay;
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
        return RecipeLayoutConstants.TRANSITION_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.TRANSITION_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REITransitionDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        // Fluid in tank
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.TRANSITION_INPUT_FLUID_X,
                sp.y + RecipeLayoutConstants.TRANSITION_INPUT_FLUID_Y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(
                sp.x + RecipeLayoutConstants.TRANSITION_ARROW1_X,
                sp.y + RecipeLayoutConstants.TRANSITION_ARROW1_Y)));

        // Catalyst item
        if (display.getInputEntries().size() > 1) {
            widgets.add(Widgets.createSlot(new Point(
                    sp.x + RecipeLayoutConstants.TRANSITION_BLOCK_X,
                    sp.y + RecipeLayoutConstants.TRANSITION_CATALYST_Y))
                    .entries(display.getInputEntries().get(1))
                    .markInput());
        }

        // Result fluid
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.TRANSITION_OUTPUT_FLUID_X,
                sp.y + RecipeLayoutConstants.TRANSITION_OUTPUT_FLUID_Y))
                .entries(display.getOutputEntries().get(0))
                .markOutput());

        return widgets;
    }
}
