package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REIHeatDisplay;
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
        return RecipeLayoutConstants.HEAT_WIDTH + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.HEAT_HEIGHT + 10;
    }

    @Override
    public List<Widget> setupDisplay(REIHeatDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        // Heat source input
        widgets.add(Widgets.createSlot(new Point(
                sp.x + RecipeLayoutConstants.HEAT_INPUT_X,
                sp.y + RecipeLayoutConstants.HEAT_INPUT_Y))
                .entries(display.getInputEntries().getFirst())
                .markInput());
        widgets.add(Widgets.createTooltip(new Rectangle(new Point(sp.x + RecipeLayoutConstants.HEAT_INPUT_X, sp.y + RecipeLayoutConstants.HEAT_INPUT_Y)),
                Component.literal(String.format("Heat: %d", display.getHeatAmount()))
                        .withStyle(ChatFormatting.WHITE)));

        return widgets;
    }
}
