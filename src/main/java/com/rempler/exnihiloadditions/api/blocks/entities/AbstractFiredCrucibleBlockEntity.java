package com.rempler.exnihiloadditions.api.blocks.entities;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.FiredCrucibleBlockBlockEntity;

public class AbstractFiredCrucibleBlockEntity extends FiredCrucibleBlockBlockEntity {
    public AbstractFiredCrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(EXABlockEntities.FIRED_CRUCIBLE_ENTITY.getType(), pos, state);
    }
}
