package com.rempler.exnihiloadditions.api;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.api.registries.AbstractBarrels;
import com.rempler.exnihiloadditions.api.registries.AbstractCrucibles;
import com.rempler.exnihiloadditions.api.registries.barrels.AbstractStoneBarrels;
import com.rempler.exnihiloadditions.api.registries.barrels.AbstractWoodBarrels;
import com.rempler.exnihiloadditions.api.registries.crucibles.AbstractFiredCrucibles;
import com.rempler.exnihiloadditions.api.registries.AbstractSieves;
import com.rempler.exnihiloadditions.api.registries.crucibles.AbstractWoodCrucibles;
import com.rempler.exnihiloadditions.api.registries.EXARegistry;
import net.minecraft.world.level.block.SoundType;

@SuppressWarnings("unused")
public class DefaultItems {
    public static final EXARegistry<AbstractCrucibles> CRUCIBLES = new EXARegistry<>("crucibles", "water");
    public static final EXARegistry<AbstractCrucibles> FIRED_CRUCIBLES = new EXARegistry<>("crucibles", "fired");
    public static final EXARegistry<AbstractSieves> SIEVES = new EXARegistry<>("sieves");
    public static final EXARegistry<AbstractBarrels> BARRELS = new EXARegistry<>("barrels");
    public static final EXARegistry<AbstractBarrels> STONE_BARRELS = new EXARegistry<>("stone_barrels");

    // BOP
    public static final AbstractWoodBarrels FIR_BARREL = addDefaultWoodBarrel("fir", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels REDWOOD_BARREL = addDefaultWoodBarrel("redwood", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels MAHOGANY_BARREL = addDefaultWoodBarrel("mahogany", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels JACARANDA_BARREL = addDefaultWoodBarrel("jacaranda", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels PALM_BARREL = addDefaultWoodBarrel("palm", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels WILLOW_BARREL = addDefaultWoodBarrel("willow", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels DEAD_BARREL = addDefaultWoodBarrel("dead", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels MAGIC_BARREL = addDefaultWoodBarrel("magic", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodBarrels UMBRAN_BARREL = addDefaultWoodBarrel("umbran", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    // Ars Nouveau
    public static final AbstractWoodBarrels ARCHWOOD_BARREL = addDefaultWoodBarrel("archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    // Aeth, falseer
    public static final AbstractWoodBarrels SKYROOT_BARREL = addDefaultWoodBarrel("skyroot", SoundType.WOOD, ExNihiloAdditions.ModIds.AETHER, false);
    // Blue Ski, falsees
    public static final AbstractWoodBarrels BLUEBRIGHT_BARREL = addDefaultWoodBarrel("bluebright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels STARLIT_BARREL = addDefaultWoodBarrel("starlit", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels FROSTBRIGHT_BARREL = addDefaultWoodBarrel("frostbright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels COMET_BARREL = addDefaultWoodBarrel("comet", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels LUNAR_BARREL = addDefaultWoodBarrel("lunar", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels DUSK_BARREL = addDefaultWoodBarrel("dusk", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodBarrels MAPLE_BARREL = addDefaultWoodBarrel("maple", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);

    // Biomes O' Plenty
    public static final AbstractStoneBarrels HELLBARK_BARREL = addDefaultStoneBarrel("hellbark", SoundType.WOOD, 1.5f, false, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    // Blue Skies
    public static final AbstractStoneBarrels CRYSTALLIZED_BARREL = addDefaultStoneBarrel("crystallized", SoundType.GLASS, 2.0f, true, ExNihiloAdditions.ModIds.BLUE_SKIES, true);


    // Biomes O' Plenty
    public static final AbstractSieves FIR_SIEVE = addDefaultSieve("fir", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves REDWOOD_SIEVE = addDefaultSieve("redwood", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves MAHOGANY_SIEVE = addDefaultSieve("mahogany", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves JACARANDA_SIEVE = addDefaultSieve("jacaranda", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves PALM_SIEVE = addDefaultSieve("palm", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves WILLOW_SIEVE = addDefaultSieve("willow", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves DEAD_SIEVE = addDefaultSieve("dead", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves MAGIC_SIEVE = addDefaultSieve("magic", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves UMBRAN_SIEVE = addDefaultSieve("umbran", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractSieves HELLBARK_SIEVE = addDefaultSieve("hellbark", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    // Ars Nouve, falseau
    public static final AbstractSieves ARCHWOOD_SIEVE = addDefaultSieve("archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    // Aeth, falseer
    public static final AbstractSieves SKYROOT_SIEVE = addDefaultSieve("skyroot", SoundType.WOOD, ExNihiloAdditions.ModIds.AETHER, false);
    // Blue Ski, falsees
    public static final AbstractSieves BLUEBRIGHT_SIEVE = addDefaultSieve("bluebright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves STARLIT_SIEVE = addDefaultSieve("starlit", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves FROSTBRIGHT_SIEVE = addDefaultSieve("frostbright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves COMET_SIEVE = addDefaultSieve("comet", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves LUNAR_SIEVE = addDefaultSieve("lunar", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves DUSK_SIEVE = addDefaultSieve("dusk", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves MAPLE_SIEVE = addDefaultSieve("maple", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractSieves CRYSTALLIZED_SIEVE = addDefaultSieve("crystallized", SoundType.GLASS, ExNihiloAdditions.ModIds.BLUE_SKIES, true);

    // Biomes O' Plenty
    public static final AbstractFiredCrucibles HELLBARK_CRUCIBLE = addDefaultLavaCrucible("hellbark", SoundType.WOOD, 1.5f, false, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    // Blue Skies
    public static final AbstractFiredCrucibles CRYSTALLIZED_CRUCIBLE = addDefaultLavaCrucible("crystallized", SoundType.GLASS, 2.0f, true, ExNihiloAdditions.ModIds.BLUE_SKIES, true);

    // Biomes O' Plenty
    public static final AbstractWoodCrucibles FIR_CRUCIBLE = addDefaultWaterCrucible("fir", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles REDWOOD_CRUCIBLE = addDefaultWaterCrucible("redwood", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles MAHOGANY_CRUCIBLE = addDefaultWaterCrucible("mahogany", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles JACARANDA_CRUCIBLE = addDefaultWaterCrucible("jacaranda", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles PALM_CRUCIBLE = addDefaultWaterCrucible("palm", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles WILLOW_CRUCIBLE = addDefaultWaterCrucible("willow", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles DEAD_CRUCIBLE = addDefaultWaterCrucible("dead", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles MAGIC_CRUCIBLE = addDefaultWaterCrucible("magic", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    public static final AbstractWoodCrucibles UMBRAN_CRUCIBLE = addDefaultWaterCrucible("umbran", SoundType.WOOD, ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, false);
    // Ars Nouve, falseau
    public static final AbstractWoodCrucibles CASCADING_ARCHWOOD_CRUCIBLE = addDefaultWaterCrucible("blue_archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    public static final AbstractWoodCrucibles BLAZING_ARCHWOOD_CRUCIBLE = addDefaultWaterCrucible("red_archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    public static final AbstractWoodCrucibles VEXING_ARCHWOOD_CRUCIBLE = addDefaultWaterCrucible("purple_archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    public static final AbstractWoodCrucibles FLOURISHING_ARCHWOOD_CRUCIBLE = addDefaultWaterCrucible("green_archwood", SoundType.WOOD, ExNihiloAdditions.ModIds.ARS_NOUVEAU, false);
    // Aeth, falseer
    public static final AbstractWoodCrucibles SKYROOT_CRUCIBLE = addDefaultWaterCrucible("skyroot", SoundType.WOOD, ExNihiloAdditions.ModIds.AETHER, false);
    public static final AbstractWoodCrucibles GOLDEN_OAK_CRUCIBLE = addDefaultWaterCrucible("golden_oak", SoundType.WOOD, ExNihiloAdditions.ModIds.AETHER, false);
    // Blue Ski, falsees
    public static final AbstractWoodCrucibles BLUEBRIGHT_CRUCIBLE = addDefaultWaterCrucible("bluebright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles STARLIT_CRUCIBLE = addDefaultWaterCrucible("starlit", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles FROSTBRIGHT_CRUCIBLE = addDefaultWaterCrucible("frostbright", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles COMET_CRUCIBLE = addDefaultWaterCrucible("comet", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles LUNAR_CRUCIBLE = addDefaultWaterCrucible("lunar", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles DUSK_CRUCIBLE = addDefaultWaterCrucible("dusk", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);
    public static final AbstractWoodCrucibles MAPLE_CRUCIBLE = addDefaultWaterCrucible("maple", SoundType.WOOD, ExNihiloAdditions.ModIds.BLUE_SKIES, false);

    private static AbstractWoodBarrels addDefaultWoodBarrel(String name, SoundType soundType, String requiredModId, boolean transparent) {
        var material = new AbstractWoodBarrels(soundType, 2.0f, false, requiredModId, transparent);
        BARRELS.register(name, material);
        return material;
    }

    private static AbstractStoneBarrels addDefaultStoneBarrel(String name, SoundType soundType, float strength, boolean requiredCorrectTool, String requiredModId, boolean transparent) {
        var material = new AbstractStoneBarrels(soundType, strength, requiredCorrectTool, requiredModId, transparent);
        STONE_BARRELS.register(name, material);
        return material;
    }

    private static AbstractSieves addDefaultSieve(String name, SoundType soundType, String requiredModId, boolean fireproof) {
        return addDefaultSieve(name, soundType, false, requiredModId, fireproof);
    }

    private static AbstractSieves addDefaultSieve(String name, SoundType soundType, boolean requiredCorrectTool, String requiredModID, boolean fireproof) {
        var material = new AbstractSieves(soundType, 2.0f, requiredCorrectTool, requiredModID, fireproof);
        SIEVES.register(name, material);
        return material;
    }

    private static AbstractFiredCrucibles addDefaultLavaCrucible(String name, SoundType soundType, float strength, boolean requiredCorrectTool, String requiredModId, boolean transparent) {
        var material = new AbstractFiredCrucibles(soundType, strength, requiredCorrectTool, requiredModId, transparent);
        FIRED_CRUCIBLES.register(name, material);
        return material;
    }

    private static AbstractWoodCrucibles addDefaultWaterCrucible(String name, SoundType soundType, String requiredModId, boolean transparent) {
        var material = new AbstractWoodCrucibles(soundType, 1.5f, false, requiredModId, transparent);
        CRUCIBLES.register(name, material);
        return material;
    }

    public static void registerItems() {
        STONE_BARRELS.search(parser -> AbstractBarrels.readFromJson(parser, AbstractStoneBarrels::new));
        BARRELS.search(parser -> AbstractBarrels.readFromJson(parser, AbstractWoodBarrels::new));
        SIEVES.search(AbstractSieves::readFromJson);
        FIRED_CRUCIBLES.search(parser -> AbstractCrucibles.readFromJson(parser, AbstractFiredCrucibles::new));
        CRUCIBLES.search(parser -> AbstractCrucibles.readFromJson(parser, AbstractWoodCrucibles::new));
    }
}
