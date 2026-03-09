package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

import java.util.List;

public class REIHeatDisplay extends BasicDisplay {
    private final int heatAmount;

    public REIHeatDisplay(HeatRecipe recipe) {
        super(
                List.of(EntryIngredients.of(resolveHeatInput(recipe.getInputBlock()))),
                List.of()
        );
        this.heatAmount = recipe.getAmount();
    }

    private static ItemLike resolveHeatInput(Block block) {
        if (block == Blocks.FIRE || block == Blocks.SOUL_FIRE) {
            return Items.FLINT_AND_STEEL;
        }
        if (block instanceof LiquidBlock liquidBlock) {
            return liquidBlock.fluid.getBucket();
        }
        return block;
    }

    public int getHeatAmount() {
        return heatAmount;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return EXAREIPlugin.HEAT;
    }
}
