package com.rempler.exnihiloadditions.api.blocks.entities;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;

public class AbstractWoodBarrelBlockEntity extends WoodBarrelBlockEntity {
    public AbstractWoodBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(EXABlockEntities.WOODEN_BARREL_ENTITY.get(), pos, state);
    }
}
