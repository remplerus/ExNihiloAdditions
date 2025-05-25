package com.rempler.exnihiloadditions.api.registries.crucibles;

import com.rempler.exnihiloadditions.api.blocks.AbstractWoodCrucibleBlock;
import com.rempler.exnihiloadditions.api.registries.AbstractCrucibles;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class AbstractWoodCrucibles extends AbstractCrucibles {
    public AbstractWoodCrucibles(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent) {
        super(soundType, strength, requiresCorrectTool, requiredModId, transparent);
    }

    @Override
    protected Block createBlock() {
        return new AbstractWoodCrucibleBlock(properties().noOcclusion().ignitedByLava());
    }
}
