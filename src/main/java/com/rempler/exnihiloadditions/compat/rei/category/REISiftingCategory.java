package com.rempler.exnihiloadditions.compat.rei.category;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.rei.display.REISiftingDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.TooltipContext;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return 166;
    }

    @Override
    public int getDisplayHeight() {
        return 78;
    }

    @Override
    public List<Widget> setupDisplay(REISiftingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        Point startPoint = new Point(bounds.x + 5, bounds.y + 5);

        widgets.add(Widgets.createRecipeBase(bounds));

        // Input block (the block being sifted)
        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 1))
                .entries(display.getInputEntries().getFirst())
                .markInput());

        // Arrow
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 22, startPoint.y + 1)));

        // Get the grouped drops from the recipe to render per-mesh rows
        Map<MeshType, List<MeshWithChance>> groupedDrops = display.getRecipe().getRolls()
                .stream().collect(Collectors.groupingBy(MeshWithChance::getMesh));

        List<MeshType> meshTypes = display.getMeshTypes();
        int yOffset = 0;

        for (MeshType mesh : meshTypes) {
            // Mesh icon on the left
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 50, startPoint.y + yOffset))
                    .entry(EntryStacks.of(MeshItem.getMesh(mesh)))
                    .disableBackground());

            // Actual drop items for this mesh on the right
            List<MeshWithChance> drops = groupedDrops.getOrDefault(mesh, List.of());
            for (int i = 0; i < drops.size() && i < 5; i++) {
                MeshWithChance drop = drops.get(i);

                // The output is the actual dropped item with chance tooltip
                widgets.add(Widgets.createSlot(new Point(startPoint.x + 70 + (i * 18), startPoint.y + yOffset))
                        .entry(EntryStacks.of(display.getRecipe().getDrop()).tooltip(
                                Component.literal(String.format("%.1f%%", drop.getChance() * 100f))
                                        .withStyle(ChatFormatting.YELLOW)))
                        .markOutput());
            }
            yOffset += 18;
        }

        return widgets;
    }
}
