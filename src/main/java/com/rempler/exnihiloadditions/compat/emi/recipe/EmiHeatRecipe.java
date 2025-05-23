package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.emi.widgets.BlockRenderWidget;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.ArrayList;
import java.util.List;

public class EmiHeatRecipe extends BasicEmiRecipe {
    private StatePropertiesPredicate properties = StatePropertiesPredicate.ANY;
    private final List<BlockState> inputStates;
    public EmiHeatRecipe(HeatRecipe recipe) {
        super(EXNEMIPlugin.HEATING, recipe.getId(), 70, 40);
        this.inputs.add(EmiStack.of(recipe.getInputBlock()).setAmount(recipe.getAmount()));
        List<BlockState> states = new ArrayList<>();
        states.add(EXNBlocks.FIRED_CRUCIBLE.block().defaultBlockState());
        states.add(recipe.getInputBlock().defaultBlockState());
        this.inputStates = states;
        if (!(recipe.getProperties() == properties)) {
            this.properties = recipe.getProperties();
        }
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        boolean isFluid = false;
        EmiIngredient input = inputs.get(0);
        long amount = input.getAmount();
        List<Component> tooltips = new ArrayList<>();
        if (inputStates.get(1).getBlock() == Blocks.FIRE || inputStates.get(1).getBlock() == Blocks.SOUL_FIRE) {
            input = EmiStack.of(Items.FLINT_AND_STEEL).setAmount(amount);
        } else if (inputStates.get(1).getBlock() == Blocks.LAVA) {
            input = EmiStack.of(Fluids.LAVA, 1000).setAmount(amount);
        } else if (inputStates.get(1).getBlock() == Blocks.WALL_TORCH || inputStates.get(1).getBlock() == Blocks.REDSTONE_WALL_TORCH || inputStates.get(1).getBlock() == Blocks.SOUL_WALL_TORCH) {
            input = EmiStack.of(inputStates.get(1).getBlock()).setAmount(amount);
            tooltips.add(Component.translatable(inputStates.get(1).getBlock().getDescriptionId().replace("torch", "wall_torch")).withStyle(ChatFormatting.DARK_RED));
        } else if (inputStates.get(1).getBlock() == Blocks.LAVA_CAULDRON) {
            tooltips.add(Component.translatable(inputStates.get(1).getBlock().getDescriptionId()).withStyle(ChatFormatting.DARK_RED));
        }
        SlotWidget slot;
        if (!isFluid) {
            slot = widgetHolder.addSlot(input, 0, 12).appendTooltip(Component.literal(String.format("Heat: %s", amount)).withStyle(ChatFormatting.WHITE));
        } else {
            slot = widgetHolder.addTank(input, 0, 12, 16, 16, 1000).appendTooltip(Component.literal(String.format("Heat: %s", amount)).withStyle(ChatFormatting.GOLD));
        }
        if (!tooltips.isEmpty()) {
            for (Component tooltip : tooltips) {
                slot.appendTooltip(tooltip);
            }
        }
        for (int i = 0; i < inputStates.size(); i++) {
            List<BlockState> tempStates = new ArrayList<>();
            tempStates.add(inputStates.get(i));
            widgetHolder.add(new BlockRenderWidget(55, 18*i + 6, tempStates, List.of(), 15f, properties));
        }
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}
