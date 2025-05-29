package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModCompatData {
    private static final Map<String, DeferredRegister<Item>> itemRegistries = new HashMap<>();
    private static final Map<String, DeferredRegister<Block>> blockRegistries = new HashMap<>();

    private static RegistryObject<Item> item(String modid, String name) {
        if (DatagenModLoader.isRunningDataGen()) {
            DeferredRegister<Item> registry = itemRegistries.computeIfAbsent(modid, key -> DeferredRegister.create(Registries.ITEM, key));
            return registry.register(name, () -> new Item(new Item.Properties()));
        } else {
            return null;
        }
    }

    private static RegistryObject<Block> block(String modid, String name) {
        if (DatagenModLoader.isRunningDataGen()) {
            DeferredRegister<Block> registry = blockRegistries.computeIfAbsent(modid, key -> DeferredRegister.create(Registries.BLOCK, key));
            return registry.register(name, () -> new Block(BlockBehaviour.Properties.of()));
        } else {
            return null;
        }
    }

    // Biomes O' Plenty
    public static final RegistryObject<Block>
            FIR_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_planks"),
            REDWOOD_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_planks"),
            MAHOGANY_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_planks"),
            JACARANDA_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_planks"),
            PALM_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_planks"),
            WILLOW_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_planks"),
            DEAD_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_planks"),
            MAGIC_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_planks"),
            UMBRAN_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_planks"),
            HELLBARK_PLANKS = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_planks"),
            FIR_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_log"),
            REDWOOD_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_log"),
            MAHOGANY_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_log"),
            JACARANDA_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_log"),
            PALM_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_log"),
            WILLOW_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_log"),
            DEAD_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_log"),
            MAGIC_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_log"),
            UMBRAN_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_log"),
            HELLBARK_LOG = block(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_log");
    public static final RegistryObject<Item>
            FIR_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_planks"),
            REDWOOD_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_planks"),
            MAHOGANY_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_planks"),
            JACARANDA_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_planks"),
            PALM_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_planks"),
            WILLOW_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_planks"),
            DEAD_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_planks"),
            MAGIC_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_planks"),
            UMBRAN_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_planks"),
            HELLBARK_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_planks"),
            FIR_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_slab"),
            REDWOOD_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_slab"),
            MAHOGANY_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_slab"),
            JACARANDA_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_slab"),
            PALM_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_slab"),
            WILLOW_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_slab"),
            DEAD_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_slab"),
            MAGIC_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_slab"),
            UMBRAN_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_slab"),
            HELLBARK_SLAB = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_slab"),
            FIR_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_log"),
            REDWOOD_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_log"),
            MAHOGANY_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_log"),
            JACARANDA_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_log"),
            PALM_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_log"),
            WILLOW_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_log"),
            DEAD_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_log"),
            MAGIC_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_log"),
            UMBRAN_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_log"),
            HELLBARK_LOG_ITEM = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_log"),
            ORIGIN_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "origin_sapling"),
            FLOWERING_OAK_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "flowering_oak_sapling"),
            SNOWBLOSSOM_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "snowblossom_sapling"),
            RAINBOW_BIRCH_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "rainbow_birch_sapling"),
            YELLOW_AUTUMN_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "yellow_autumn_sapling"),
            ORANGE_AUTUMN_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "orange_autumn_sapling"),
            MAPLE_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "maple_sapling"),
            FIR_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "fir_sapling"),
            REDWOOD_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "redwood_sapling"),
            MAHOGANY_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "mahogany_sapling"),
            JACARANDA_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "jacaranda_sapling"),
            PALM_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "palm_sapling"),
            WILLOW_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "willow_sapling"),
            DEAD_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "dead_sapling"),
            MAGIC_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "magic_sapling"),
            UMBRAN_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "umbran_sapling"),
            HELLBARK_SAPLING = item(ExNihiloAdditions.ModIds.BIOMES_O_PLENTY, "hellbark_sapling");
    // Ars Nouveau
    public static final RegistryObject<Block>
            CASCADING_ARCHWOOD_LOG = block(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "blue_archwood_log"),
            BLAZING_ARCHWOOD_LOG = block(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "red_archwood_log"),
            VEXING_ARCHWOOD_LOG = block(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "purple_archwood_log"),
            FLOURISHING_ARCHWOOD_LOG = block(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "green_archwood_log"),
            ARCHWOOD_PLANKS = block(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "archwood_planks");
    public static final RegistryObject<Item>
            BLUE_ARCHWOOD_SAPLING = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "blue_archwood_sapling"),
            RED_ARCHWOOD_SAPLING = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "red_archwood_sapling"),
            PURPLE_ARCHWOOD_SAPLING = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "purple_archwood_sapling"),
            GREEN_ARCHWOOD_SAPLING = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "green_archwood_sapling"),
            SOURCEBERRY = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "sourceberry_bush"),
            CASCADING_ARCHWOOD_LOG_ITEM = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "blue_archwood_log"),
            BLAZING_ARCHWOOD_LOG_ITEM = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "red_archwood_log"),
            VEXING_ARCHWOOD_LOG_ITEM = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "purple_archwood_log"),
            FLOURISHING_ARCHWOOD_LOG_ITEM = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "green_archwood_log"),
            ARCHWOOD_SLAB = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "archwood_slab"),
            ARCHWOOD_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.ARS_NOUVEAU, "archwood_planks");
    // Aether
    public static final RegistryObject<Block>
            SKYROOT_PLANKS = block(ExNihiloAdditions.ModIds.AETHER, "skyroot_planks"),
            SKYROOT_LOG = block(ExNihiloAdditions.ModIds.AETHER, "skyroot_log"),
            GOLDEN_OAK_LOG = block(ExNihiloAdditions.ModIds.AETHER, "golden_oak_log");
    public static final RegistryObject<Item>
            SKYROOT_SLAB = item(ExNihiloAdditions.ModIds.AETHER, "skyroot_slab"),
            SKYROOT_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.AETHER, "skyroot_planks"),
            GOLDEN_OAK_LOG_ITEM = item(ExNihiloAdditions.ModIds.AETHER, "golden_oak_log"),
            SKYROOT_LOG_ITEM = item(ExNihiloAdditions.ModIds.AETHER, "skyroot_log");
    // Blue Skies
    public static final RegistryObject<Block>
            BLUEBRIGHT_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "bluebright_planks"),
            STARLIT_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "starlit_planks"),
            FROSTBRIGHT_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "frostbright_planks"),
            COMET_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "comet_planks"),
            LUNAR_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "lunar_planks"),
            DUSK_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "dusk_planks"),
            MAPLE_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "maple_planks"),
            CRYSTALLIZED_PLANKS = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "crystallized_planks"),
            BLUEBRIGHT_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "bluebright_log"),
            STARLIT_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "starlit_log"),
            FROSTBRIGHT_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "frostbright_log"),
            COMET_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "comet_log"),
            LUNAR_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "lunar_log"),
            DUSK_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "dusk_log"),
            MAPLE_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "maple_log"),
            CRYSTALLIZED_LOG = block(ExNihiloAdditions.ModIds.BLUE_SKIES, "crystallized_log");
    public static final RegistryObject<Item>
            BLUEBRIGHT_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "bluebright_planks"),
            STARLIT_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "starlit_planks"),
            FROSTBRIGHT_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "frostbright_planks"),
            COMET_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "comet_planks"),
            LUNAR_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "lunar_planks"),
            DUSK_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "dusk_planks"),
            MAPLE_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "maple_planks"),
            CRYSTALLIZED_PLANKS_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "crystallized_planks"),
            BLUEBRIGHT_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "bluebright_slab"),
            STARLIT_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "starlit_slab"),
            FROSTBRIGHT_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "frostbright_slab"),
            COMET_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "comet_slab"),
            LUNAR_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "lunar_slab"),
            DUSK_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "dusk_slab"),
            MAPLE_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "maple_slab"),
            CRYSTALLIZED_SLAB = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "crystallized_slab"),
            BLUEBRIGHT_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "bluebright_log"),
            STARLIT_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "starlit_log"),
            FROSTBRIGHT_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "frostbright_log"),
            COMET_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "comet_log"),
            LUNAR_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "lunar_log"),
            DUSK_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "dusk_log"),
            MAPLE_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "maple_log"),
            CRYSTALLIZED_LOG_ITEM = item(ExNihiloAdditions.ModIds.BLUE_SKIES, "crystallized_log");

    public static void registerModData() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();

        for (var registry : itemRegistries.values()) {
            registry.register(modBus);
        }
        for (var registry : blockRegistries.values()) {
            registry.register(modBus);
        }
    }
}
