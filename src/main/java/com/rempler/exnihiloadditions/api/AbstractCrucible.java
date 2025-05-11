package com.rempler.exnihiloadditions.api;

import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCrucible extends AbstractItemLike {
    public AbstractCrucible(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId) {
        super(soundType, strength, requiresCorrectTool, requiredModId);
    }

    @Nullable
    public static <T extends AbstractCrucible> T readFromJson(EXAJsonParser parser, Factory<T> factory) {
        SoundType soundtype = parser.getSoundType();
        float strength = parser.getStrength();
        boolean requiresCorrectTool = parser.getOptionalBoolean("required_correct_tool");
        String requiredModId = parser.getRequiredModID();

        if (parser.error) {
            return null;
        } else {
            return factory.create(soundtype, strength, requiresCorrectTool, requiredModId);
        }
    }

    public interface Factory<T> {
        T create(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId);
    }
}
