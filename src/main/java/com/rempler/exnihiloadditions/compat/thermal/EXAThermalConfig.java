package com.rempler.exnihiloadditions.compat.thermal;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import java.nio.file.Path;

public class EXAThermalConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;
    private static final String CATEGORY_ORE = "ore";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    // Ore
    private static ForgeConfigSpec.BooleanValue enableOsmium;

    static {
        COMMON_BUILDER.comment("Compatibility Configs").push(CATEGORY_ORE);
        oreConfigs();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static boolean enableOsmium() {
        return enableOsmium.get();
    }

    private static void oreConfigs() {
        enableOsmium =
                COMMON_BUILDER
                        .comment(
                                "Enable osmium ore pieces, chunks and ingots if they exist. 'enableOreOverride' in the main Ex Nihilo config must be true for this to work. (Default: true)")
                        .define("enableOsmium", true);
    }

    public static void loadConfig(@Nonnull ForgeConfigSpec spec, @Nonnull Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}
