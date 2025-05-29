package com.rempler.exnihiloadditions.api.blocks;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.entity.FiredCrucibleBlockBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AbstractFiredCrucibleBlock extends CrucibleBlock implements EntityBlock {
    public AbstractFiredCrucibleBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new FiredCrucibleBlockBlockEntity(EXABlockEntities.FIRED_CRUCIBLE_ENTITY.get(), pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
        return !level.isClientSide ? (level1, blockPos, blockState, t) -> {
            if (t instanceof FiredCrucibleBlockBlockEntity tile) {
                tile.tickServer();
            }

        } : null;
    }
}
