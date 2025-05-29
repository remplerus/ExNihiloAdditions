package com.rempler.exnihiloadditions.api.blocks;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AbstractSieveBlock extends SieveBlock implements EntityBlock {
    public AbstractSieveBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SieveBlockEntity(EXABlockEntities.SIEVE_ENTITY.get(), pos, state);
    }
}
