package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.compat.emi.client.EXAEMIClientSetup;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCItems;
import com.rempler.exnihiloadditions.compat.tfc.client.EXNATFCClientSetup;
import com.rempler.exnihiloadditions.registers.EXABlockEntities;
import com.rempler.exnihiloadditions.registers.EXABlocks;
import com.rempler.exnihiloadditions.registers.EXAItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

@Mod(ExNihiloAdditions.ModIds.MODID)
public class ExNihiloAdditions {
    public static final Logger LOGGER = LoggerFactory.getLogger("ExNihiloAdditions");
    public static boolean isTFCLoaded = ModList.get().isLoaded(ModIds.TFC);
    public static boolean isEMILoaded = ModList.get().isLoaded(ModIds.EMI);

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ModIds.MODID, path);
    }

    public ExNihiloAdditions() {
        DefaultItems.registerItems();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (isTFCLoaded) {
            LOGGER.info("TFC is loaded, registering TFC compat");
            eventBus.addListener(EXNATFCClientSetup::init);
        }
        if (isEMILoaded) {
            LOGGER.info("EMI is loaded, registering EMI compat");
            EXAEMIClientSetup.register(eventBus);
        }

        FMLJavaModLoadingContext.get()
                .getModEventBus()
                .addListener((RegisterEvent event) -> {
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                        ForgeBlockRegistry registry = new ForgeBlockRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                        for (BlockDefinition<?> definition : EXABlocks.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                        ForgeBlockEntityTypeRegistry registry = new ForgeBlockEntityTypeRegistry();
                        if (isTFCLoaded) {
                            for (BlockEntityTypeDefinition<?> definition : EXNATFCBlockEntites.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                        for (BlockEntityTypeDefinition<?> definition : EXABlockEntities.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                        ForgeItemRegistry registry = new ForgeItemRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                            for (ItemDefinition<?> definition : EXNATFCItems.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                        for (BlockDefinition<?> definition : EXABlocks.getDefinitions()) {
                            registry.register(definition);
                        }
                        for (ItemDefinition<?> definition : EXAItems.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
                        ForgeCreativeModeTabRegistry registry = new ForgeCreativeModeTabRegistry();
                        for (CreativeModeTabDefinition definition : EXNACreativeModeTabs.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                });
    }

    public static class ModIds {
        public static final String MODID = "exnihiloadditions";
        public static final String TFC = "tfc";
        public static final String EMI = "emi";
        public static final String BIOMES_O_PLENTY = "biomesoplenty";
        public static final String ARS_NOUVEAU = "ars_nouveau";
        public static final String BLUE_SKIES = "blueskies";
        public static final String AETHER = "aether";
    }
}
