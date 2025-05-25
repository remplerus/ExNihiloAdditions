package com.rempler.exnihiloadditions.api.registries;

import com.google.common.collect.Iterables;
import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.api.EXAJsonParser;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCrucibles extends AbstractItemLike {
    public static final Set<Block> TRANSPARENT_CRUCIBLES = new HashSet<>();
    public final boolean transparent;

    public AbstractCrucibles(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent) {
        super(soundType, strength, requiresCorrectTool, requiredModId);
        this.transparent = transparent;
    }

    @Nullable
    public static <T extends AbstractCrucibles> T readFromJson(EXAJsonParser parser, Factory<T> factory) {
        SoundType soundtype = parser.getSoundType();
        float strength = parser.getStrength();
        boolean requiresCorrectTool = parser.getOptionalBoolean("required_correct_tool");
        String requiredModId = parser.getRequiredModID();
        boolean transparent = parser.getOptionalBoolean("transparent");

        if (parser.isError()) {
            return null;
        } else {
            return factory.create(soundtype, strength, requiresCorrectTool, requiredModId, transparent);
        }
    }

    public static void loadTransparentBlocks() {
        for (var material : Iterables.concat(DefaultItems.CRUCIBLES, DefaultItems.FIRED_CRUCIBLES)) {
            if (material.transparent) {
                TRANSPARENT_CRUCIBLES.add(material.getBlock());
            }
        }
    }

    public interface Factory<T> {
        T create(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent);
    }
}
