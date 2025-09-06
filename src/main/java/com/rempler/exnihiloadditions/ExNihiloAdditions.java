package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.compat.botania.EXABotaniaConfig;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaLootModifiers;
import com.rempler.exnihiloadditions.compat.emi.client.EXAEMIClientSetup;
import com.rempler.exnihiloadditions.compat.tetra.EXATetra;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlockEntites;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCItems;
import com.rempler.exnihiloadditions.compat.tfc.client.EXATFCClientSetup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.RegisterEvent;
import novamachina.novacore.bootstrap.ForgeBlockEntityTypeRegistry;
import novamachina.novacore.bootstrap.ForgeBlockRegistry;
import novamachina.novacore.bootstrap.ForgeCreativeModeTabRegistry;
import novamachina.novacore.bootstrap.ForgeItemRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ExNihiloAdditions.MODID)
public class ExNihiloAdditions {
    public static final Logger LOGGER = LoggerFactory.getLogger("ExNihiloAdditions");
    public static final String MODID = "exnihiloadditions";
    public static boolean isTFCLoaded = ModList.get().isLoaded("tfc");
    public static boolean isEMILoaded = ModList.get().isLoaded("emi");
    public static boolean isTetraLoaded = ModList.get().isLoaded("tetra");
    public static boolean isBotaniaLoaded = ModList.get().isLoaded("botania");

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    public ExNihiloAdditions() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EXABotaniaConfig.COMMON_CONFIG);
        EXABotaniaConfig.loadConfig(EXABotaniaConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID+"-common.toml"));
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (isTFCLoaded) {
            LOGGER.info("TFC is loaded, registering TFC compat");
            eventBus.addListener(EXATFCClientSetup::init);
        }
        if (isEMILoaded) {
            LOGGER.info("EMI is loaded, registering EMI compat");
            EXAEMIClientSetup.register(eventBus);
        }
        if (isTetraLoaded) {
            LOGGER.info("Tetra is loaded, registering Tetra compat");
            new EXATetra(eventBus);
        }
        if (isBotaniaLoaded) {
            EXABotaniaLootModifiers.LOOT_MODIFIERS.register(eventBus);
        }

        eventBus.addListener((RegisterEvent event) -> {
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                        ForgeBlockRegistry registry = new ForgeBlockRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                        ForgeBlockEntityTypeRegistry registry = new ForgeBlockEntityTypeRegistry();
                        if (isTFCLoaded) {
                            for (BlockEntityTypeDefinition<?> definition : EXATFCBlockEntites.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                        ForgeItemRegistry registry = new ForgeItemRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                            for (ItemDefinition<?> definition : EXATFCItems.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                        if (isBotaniaLoaded) {
                            for (ItemDefinition<?> definition : EXABotaniaItems.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
                        ForgeCreativeModeTabRegistry registry = new ForgeCreativeModeTabRegistry();
                        for (CreativeModeTabDefinition definition : EXACreativeModeTabs.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                });
    }
}
