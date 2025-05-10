package com.rempler.exnihiloadditions.compat.tfc.blocks;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.WoodCrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.entity.WoodCrucibleBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EXNATFCCrucibleBlock extends WoodCrucibleBlock {

    public EXNATFCCrucibleBlock() {}

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new WoodCrucibleBlockEntity(EXNATFCBlockEntites.WOODEN_CRUCIBLE_ENTITY.getType(), pos, state);
    }
}
