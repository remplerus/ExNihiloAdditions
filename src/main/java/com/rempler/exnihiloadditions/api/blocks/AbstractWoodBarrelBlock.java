package com.rempler.exnihiloadditions.api.blocks;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

public class AbstractWoodBarrelBlock extends BarrelBlock implements EntityBlock {
    public AbstractWoodBarrelBlock(Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new WoodBarrelBlockEntity(EXABlockEntities.WOODEN_BARREL_ENTITY.getType(), pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        return !level.isClientSide ? (level1, blockPos, blockState, t) -> {
            if (t instanceof WoodBarrelBlockEntity tile) {
                tile.tickServer();
            }

        } : null;
    }
}
