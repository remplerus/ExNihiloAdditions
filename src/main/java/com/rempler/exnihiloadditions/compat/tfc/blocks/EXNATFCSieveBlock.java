package com.rempler.exnihiloadditions.compat.tfc.blocks;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.WoodSieveBlock;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EXNATFCSieveBlock extends WoodSieveBlock {
    public EXNATFCSieveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SieveBlockEntity(EXNATFCBlockEntites.WOODEN_SIEVE_ENTITY.getType(), pos, state);
    }
}
