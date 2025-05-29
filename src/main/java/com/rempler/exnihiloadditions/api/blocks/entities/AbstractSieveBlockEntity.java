package com.rempler.exnihiloadditions.api.blocks.entities;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;

public class AbstractSieveBlockEntity extends SieveBlockEntity {
    public AbstractSieveBlockEntity(BlockPos pos, BlockState state) {
        super(EXABlockEntities.SIEVE_ENTITY.get(), pos, state);
    }
}
