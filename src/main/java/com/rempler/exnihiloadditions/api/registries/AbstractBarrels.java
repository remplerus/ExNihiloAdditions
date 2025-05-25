package com.rempler.exnihiloadditions.api.registries;

import com.google.common.collect.Iterables;
import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.api.EXAJsonParser;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractBarrels extends AbstractItemLike {
    public static final Set<Block> TRANSPARENT_BARRELS = new HashSet<>();
    public final boolean transparent;

    public AbstractBarrels(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent) {
        super(soundType, strength, requiresCorrectTool, requiredModId);
        this.transparent = transparent;
    }

    @Nullable
    public static <T extends AbstractBarrels> T readFromJson(EXAJsonParser parser, Factory<T> factory) {
        SoundType soundType = parser.getSoundType();
        float strength = parser.getStrength();
        boolean requiresCorrectTool = parser.getOptionalBoolean("requires_correct_tool");
        String requiredModId = parser.getRequiredModID();
        boolean transparent = parser.getOptionalBoolean("transparent");

        if (parser.isError()) {
            return null;
        } else {
            return factory.create(soundType, strength, requiresCorrectTool, requiredModId, transparent);
        }
    }

    public static void loadTransparentBlocks() {
        for (AbstractBarrels barrel : Iterables.concat(DefaultItems.BARRELS, DefaultItems.STONE_BARRELS)) {
            if (barrel.transparent) {
                TRANSPARENT_BARRELS.add(barrel.getBlock());
            }
        }
    }

    public interface Factory<T> {
        T create(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId, boolean transparent);
    }
}
