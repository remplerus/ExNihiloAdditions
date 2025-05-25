package com.rempler.exnihiloadditions.api.registries;

import com.rempler.exnihiloadditions.api.EXAJsonParser;
import com.rempler.exnihiloadditions.api.blocks.AbstractSieveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

public class AbstractSieves extends AbstractItemLike {
    private final boolean fireproof;

    public AbstractSieves(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean fireproof) {
        super(soundType, strength, requiresCorrectTool, requiredModId);
        this.fireproof = fireproof;
    }

    @Override
    protected Block createBlock() {
        if (!fireproof) {
            return new AbstractSieveBlock(properties().noOcclusion().ignitedByLava());
        } else {
            return new AbstractSieveBlock(properties().noOcclusion());
        }
    }

    @Nullable
    public static AbstractSieves readFromJson(EXAJsonParser parser) {
        SoundType soundType = parser.getSoundType();
        float strength = parser.getStrength();
        boolean needsCorrectTool = parser.getOptionalBoolean("needs_correct_tool");
        String requiredModId = parser.getRequiredModID();
        boolean fireproof = parser.getOptionalBoolean("fireproof");

        if (parser.isError()) {
            return null;
        } else {
            return new AbstractSieves(soundType, strength, needsCorrectTool, requiredModId, fireproof);
        }
    }
}
