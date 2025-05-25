package com.rempler.exnihiloadditions.api.registries.barrels;

import com.rempler.exnihiloadditions.api.blocks.AbstractStoneBarrelBlock;
import com.rempler.exnihiloadditions.api.blocks.AbstractWoodBarrelBlock;
import com.rempler.exnihiloadditions.api.registries.AbstractBarrels;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class AbstractStoneBarrels extends AbstractBarrels {
    public AbstractStoneBarrels(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent) {
        super(soundType, strength, requiresCorrectTool, requiredModId, transparent);
    }

    @Override
    protected Block createBlock() {
        return new AbstractStoneBarrelBlock(properties().noOcclusion());
    }
}
