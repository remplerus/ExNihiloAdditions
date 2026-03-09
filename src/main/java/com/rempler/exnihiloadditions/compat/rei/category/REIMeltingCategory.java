package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIMeltingDisplay;
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
        return RecipeLayoutConstants.MELTING_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.MELTING_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REIMeltingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        // Input item
        widgets.add(Widgets.createSlot(new Point(sp.x, sp.y))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(sp.x + RecipeLayoutConstants.ARROW_OFFSET_X, sp.y + 1)));

        // Fluid output
        if (!display.getOutputEntries().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(sp.x + RecipeLayoutConstants.OUTPUT_OFFSET_X, sp.y))
                    .entries(display.getOutputEntries().get(0))
                    .markOutput());
        }

        return widgets;
    }
}
