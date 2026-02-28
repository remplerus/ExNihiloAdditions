package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;

import java.util.List;
import java.util.stream.Collectors;

public class REIMeltingDisplay extends BasicDisplay {
    private final CrucibleType crucibleType;
    private final FluidStack resultFluid;
    private final int fluidAmount;

    public REIMeltingDisplay(MeltingRecipe recipe, List<ItemStack> inputItems) {
        super(
                inputItems.stream()
                        .map(EntryIngredients::of)
                        .collect(Collectors.toList()),
                List.of(EntryIngredient.of(EntryStacks.of(
                        dev.architectury.fluid.FluidStack.create(
                                recipe.getResultFluid().getFluid(),
                                recipe.getResultFluid().getAmount()
                        )
                )))
        );
        this.crucibleType = recipe.getCrucibleType();
        this.resultFluid = recipe.getResultFluid();
        this.fluidAmount = recipe.getResultFluid().getAmount();
    }

    public CrucibleType getCrucibleType() {
        return crucibleType;
    }

    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public int getFluidAmount() {
        return fluidAmount;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        if (crucibleType == CrucibleType.WOOD) {
            return EXAREIPlugin.MELTING;
        } else {
            return EXAREIPlugin.FIRED_MELTING;
        }
    }
}
