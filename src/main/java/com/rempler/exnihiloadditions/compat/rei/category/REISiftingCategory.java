package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REISiftingDisplay;
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
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Sifting category for REI that matches EMI's layout:
 * - Input block on the left
 * - Arrow
 * - Grid of output items (each with its corresponding mesh icon below)
 */
public class REISiftingCategory implements DisplayCategory<REISiftingDisplay> {
    private final boolean isWet;

    public REISiftingCategory(boolean isWet) {
        this.isWet = isWet;
    }

    @Override
    public CategoryIdentifier<? extends REISiftingDisplay> getCategoryIdentifier() {
        return isWet ? EXAREIPlugin.WET_SIFTING : EXAREIPlugin.DRY_SIFTING;
    }

    @Override
    public Component getTitle() {
        return isWet
                ? Component.translatable("emi.category.exnihiloadditions.water_sifting")
                : Component.translatable("emi.category.exnihiloadditions.sifting");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(EXNBlocks.ACACIA_SIEVE.itemStack());
    }

    @Override
    public int getDisplayWidth(REISiftingDisplay display) {
        int outputCount = display.getOutputEntries().size();
        return RecipeLayoutConstants.siftDisplayWidth(outputCount) + 10;
    }

    @Override
    public int getDisplayHeight() {
        return RecipeLayoutConstants.siftDisplayHeight(7) + 10;
    }

    @Override
    public List<Widget> setupDisplay(REISiftingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point sp = new Point(bounds.x + 5, bounds.y + 5);
        widgets.add(Widgets.createRecipeBase(bounds));

        int outputCount = display.getOutputEntries().size();
        int contentHeight = RecipeLayoutConstants.siftDisplayHeight(outputCount);
        int y = contentHeight - RecipeLayoutConstants.SLOT_SIZE;

        // Input block
        widgets.add(Widgets.createSlot(new Point(sp.x, sp.y + y / 2))
                .entries(display.getInputEntries().get(0))
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(
                sp.x + RecipeLayoutConstants.ARROW_OFFSET_X, sp.y + y / 2 + 1)));

        // Output items with mesh indicators below (matching EMI layout)
        List<MeshType> meshTypes = display.getMeshTypes();
        for (int i = 0; i < outputCount && i < display.getOutputEntries().size(); i++) {
            int slotX = sp.x + RecipeLayoutConstants.SIFT_OUTPUT_START_X
                    + (i % RecipeLayoutConstants.SIFT_OUTPUT_COLS) * RecipeLayoutConstants.SLOT_SIZE;
            int slotY = sp.y + (i / RecipeLayoutConstants.SIFT_OUTPUT_COLS) * RecipeLayoutConstants.SLOT_SIZE;

            // Output item
            widgets.add(Widgets.createSlot(new Point(slotX, slotY))
                    .entries(display.getOutputEntries().get(i))
                    .markOutput());

            // Mesh icon below each output (like EMI does)
            if (i < meshTypes.size()) {
                MeshType mesh = meshTypes.get(i);
                widgets.add(Widgets.createSlot(new Point(slotX, slotY + RecipeLayoutConstants.SIFT_MESH_OFFSET_Y))
                        .entry(EntryStacks.of(MeshItem.getMesh(mesh)))
                        .disableBackground());
            }
        }

        return widgets;
    }
}
