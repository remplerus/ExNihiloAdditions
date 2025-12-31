package com.rempler.exnihiloadditions.compat.tfc.blocks;

import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.WoodSieveBlock;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EXATFCSieveBlock extends WoodSieveBlock {
    public EXATFCSieveBlock() { }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SieveBlockEntity(EXATFCBlockEntites.WOODEN_SIEVE_ENTITY.getType(), pos, state);
    }
}
