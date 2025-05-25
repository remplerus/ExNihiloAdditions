package com.rempler.exnihiloadditions.api.blocks.entities;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.StoneBarrelBlockEntity;

public class AbstractStoneBarrelBlockEntity extends StoneBarrelBlockEntity {
    public AbstractStoneBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(EXABlockEntities.STONE_BARREL_ENTITY.getType(), pos, state);
    }
}
