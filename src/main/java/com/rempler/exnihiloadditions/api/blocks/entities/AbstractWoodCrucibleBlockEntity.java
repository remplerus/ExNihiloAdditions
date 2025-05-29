package com.rempler.exnihiloadditions.api.blocks.entities;

import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.entity.WoodCrucibleBlockEntity;

public class AbstractWoodCrucibleBlockEntity extends WoodCrucibleBlockEntity {
    public AbstractWoodCrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(EXABlockEntities.WOODEN_CRUCIBLE_ENTITY.get(), pos, state);
    }
}
