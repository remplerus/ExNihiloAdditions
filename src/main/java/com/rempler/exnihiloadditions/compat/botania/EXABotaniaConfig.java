package com.rempler.exnihiloadditions.compat.botania;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import vazkii.botania.api.BotaniaAPI;

import javax.annotation.Nonnull;
import java.nio.file.Path;

public class EXABotaniaConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.IntValue hammerLivingwoodValue;
    private static final ForgeConfigSpec.IntValue hammerDreamwoodValue;
    private static final ForgeConfigSpec.IntValue hammerLivingrockValue;
    private static final ForgeConfigSpec.IntValue crookLivingwoodValue;
    private static final ForgeConfigSpec.IntValue crookDreamwoodValue;
    private static final ForgeConfigSpec.IntValue crookLivingrockValue;
    private static final ForgeConfigSpec.IntValue hammerManasteelValue;
    private static final ForgeConfigSpec.IntValue hammerElementiumValue;
    private static final ForgeConfigSpec.IntValue hammerTerrasteelValue;
    private static final ForgeConfigSpec.IntValue crookManasteelValue;
    private static final ForgeConfigSpec.IntValue crookElementiumValue;
    private static final ForgeConfigSpec.IntValue crookTerrasteelValue;
    private static final ForgeConfigSpec.DoubleValue pixieSpawnChance;
    private static final ForgeConfigSpec.DoubleValue detectSpawnRange;

    public static int getHammerLivingwoodValue() {
        return hammerLivingwoodValue.get();
    }
    public static int getHammerDreamwoodValue() {
        return hammerDreamwoodValue.get();
    }
    public static int getHammerLivingrockValue() {
        return hammerLivingrockValue.get();
    }
    public static int getCrookLivingwoodValue() {
        return crookLivingwoodValue.get();
    }
    public static int getCrookDreamwoodValue() {
        return crookDreamwoodValue.get();
    }
    public static int getCrookLivingrockValue() {
        return crookLivingrockValue.get();
    }

    public static int getHammerManasteelValue() {
        return hammerManasteelValue.get();
    }
    public static int getHammerElementiumValue() {
        return hammerElementiumValue.get();
    }
    public static int getHammerTerrasteelValue() {
        return hammerTerrasteelValue.get();
    }
    public static int getCrookManasteelValue() {
        return crookManasteelValue.get();
    }
    public static int getCrookElementiumValue() {
        return crookElementiumValue.get();
    }
    public static int getCrookTerrasteelValue() {
        return crookTerrasteelValue.get();
    }

    public static double getPixieSpawnChance() {
        return pixieSpawnChance.get();
    }
    public static double getDetectSpawnRange() {
        return detectSpawnRange.get();
    }

    static {
        COMMON_BUILDER.push("Botania");
        hammerLivingwoodValue = COMMON_BUILDER
                .comment("Durability of Livingwood hammer")
                .defineInRange("hammerLivingwood", 68, 1, Integer.MAX_VALUE);
        hammerDreamwoodValue = COMMON_BUILDER
                .comment("Durability of Dreamwood hammer")
                .defineInRange("hammerDreamwood", 102, 1, Integer.MAX_VALUE);
        hammerLivingrockValue = COMMON_BUILDER
                .comment("Durability of Livingrock hammer")
                .defineInRange("hammerLivingrock", 191, 1, Integer.MAX_VALUE);
        crookLivingwoodValue = COMMON_BUILDER
                .comment("Durability of Livingwood crook")
                .defineInRange("crookLivingwood", 68, 1, Integer.MAX_VALUE);
        crookDreamwoodValue = COMMON_BUILDER
                .comment("Durability of Dreamwood crook")
                .defineInRange("crookDreamwood", 102, 1, Integer.MAX_VALUE);
        crookLivingrockValue = COMMON_BUILDER
                .comment("Durability of Livingrock crook")
                .defineInRange("crookLivingrock", 191, 1, Integer.MAX_VALUE);
        hammerManasteelValue = COMMON_BUILDER
                .comment("Durability of Manasteel hammer")
                .defineInRange("hammerManasteel", BotaniaAPI.instance().getManasteelItemTier().getUses(), 1, Integer.MAX_VALUE);
        hammerElementiumValue = COMMON_BUILDER
                .comment("Durability of Elementium hammer")
                .defineInRange("hammerElementium", BotaniaAPI.instance().getElementiumItemTier().getUses(), 1, Integer.MAX_VALUE);
        hammerTerrasteelValue = COMMON_BUILDER
                .comment("Durability of Terrasteel hammer")
                .defineInRange("hammerTerrasteel", BotaniaAPI.instance().getTerrasteelItemTier().getUses(), 1, Integer.MAX_VALUE);
        crookManasteelValue = COMMON_BUILDER
                .comment("Durability of Manasteel crook")
                .defineInRange("crookManasteel", BotaniaAPI.instance().getManasteelItemTier().getUses(), 1, Integer.MAX_VALUE);
        crookElementiumValue = COMMON_BUILDER
                .comment("Durability of Elementium crook")
                .defineInRange("crookElementium", BotaniaAPI.instance().getElementiumItemTier().getUses(), 1, Integer.MAX_VALUE);
        crookTerrasteelValue = COMMON_BUILDER
                .comment("Durability of Terrasteel crook")
                .defineInRange("crookTerrasteel", BotaniaAPI.instance().getTerrasteelItemTier().getUses(), 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Botania Pixie Spawning");
        pixieSpawnChance = COMMON_BUILDER
                .comment("Chance of a Pixie spawning when using an Elementium Crook or Hammer")
                .defineInRange("pixieSpawnChance", 0.05, 0.0, 1.0);
        detectSpawnRange = COMMON_BUILDER
                .comment("Range in which the Botania Pixie will be detected for spawning")
                .defineInRange("detectSpawnRange", 16.0, 1.0, 64.0);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void loadConfig(@Nonnull ForgeConfigSpec spec, @Nonnull Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}
