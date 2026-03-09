package com.rempler.exnihiloadditions.custom.blocks;

import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.FiredCrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.WoodCrucibleBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomCrucibleBlock extends WoodCrucibleBlock {

    public CustomCrucibleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CustomCrucibleBlockEntity(pos, state);
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state,
                            @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        String variantId = stack.getOrDefault(EXNACustomComponents.VARIANT_ID.get(), "");
        if (!variantId.isEmpty() && level.getBlockEntity(pos) instanceof CustomCrucibleBlockEntity be) {
            be.setVariantId(variantId);
        }
    }
}
