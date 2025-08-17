package com.rempler.exnihiloadditions.compat.tfc.blocks;

import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.WoodBarrelBlock;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EXATFCBarrelBlock extends WoodBarrelBlock {

    public EXATFCBarrelBlock() {}

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new WoodBarrelBlockEntity(EXATFCBlockEntites.WOODEN_BARREL_ENTITY.getType(), pos, state);
    }
}
