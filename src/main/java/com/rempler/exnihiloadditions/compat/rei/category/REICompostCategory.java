package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REICompostDisplay;
import com.rempler.exnihiloadditions.compat.shared.RecipeLayoutConstants;
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
import novamachina.exnihilosequentia.common.Config;
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
        return RecipeLayoutConstants.COMPOST_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.COMPOST_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REICompostDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createSlot(new Point(sp.x, sp.y))
                .entries(display.getInputEntries().getFirst())
                .markInput());
        widgets.add(Widgets.createTooltip(new Rectangle(new Point(sp.x, sp.y)), Component.literal(String.format("Amount: %d / %d",
                        display.getSolidAmount(), Config.getBarrelMaxSolidAmount()))
                .withStyle(ChatFormatting.GRAY)));

        widgets.add(Widgets.createArrow(new Point(sp.x + RecipeLayoutConstants.ARROW_OFFSET_X, sp.y + 1)));

        widgets.add(Widgets.createSlot(new Point(sp.x + RecipeLayoutConstants.OUTPUT_OFFSET_X, sp.y))
                .entries(display.getOutputEntries().getFirst())
                .markOutput());

        return widgets;
    }
}
