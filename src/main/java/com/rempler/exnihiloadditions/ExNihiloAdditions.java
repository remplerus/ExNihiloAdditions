package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.compat.emi.client.EXAEMIClientSetup;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCItems;
import com.rempler.exnihiloadditions.compat.tfc.client.EXNATFCClientSetup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import novamachina.novacore.bootstrap.core.registries.NeoforgeBlockEntityTypeRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeBlockRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeCreativeModeTabRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeItemRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.entity.BlockEntityTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ExNihiloAdditions.MODID)
public class ExNihiloAdditions {
    public static final Logger LOGGER = LoggerFactory.getLogger("ExNihiloAdditions");
    public static final String MODID = "exnihiloadditions";
    public static boolean isTFCLoaded = ModList.get().isLoaded("tfc");
    public static boolean isEMILoaded = ModList.get().isLoaded("emi");

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public ExNihiloAdditions(ModContainer container, IEventBus eventBus) {
        if (isTFCLoaded) {
            LOGGER.info("TFC is loaded, registering TFC compat");
            eventBus.addListener(EXNATFCClientSetup::init);
        }
        if (isEMILoaded) {
            LOGGER.info("EMI is loaded, registering EMI compat");
            EXAEMIClientSetup.register(eventBus);
        }

        eventBus.addListener((RegisterEvent event) -> {
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                        NeoforgeBlockRegistry registry = new NeoforgeBlockRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                        NeoforgeBlockEntityTypeRegistry registry = new NeoforgeBlockEntityTypeRegistry();
                        if (isTFCLoaded) {
                            for (BlockEntityTypeDefinition<?> definition : EXNATFCBlockEntites.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                        NeoforgeItemRegistry registry = new NeoforgeItemRegistry();
                        if (isTFCLoaded) {
                            for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                                registry.register(definition);
                            }
                            for (ItemDefinition<?> definition : EXNATFCItems.getDefinitions()) {
                                registry.register(definition);
                            }
                        }
                    }
                    if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
                        NeoforgeCreativeModeTabRegistry registry = new NeoforgeCreativeModeTabRegistry();
                        for (CreativeModeTabDefinition definition : EXNACreativeModeTabs.getDefinitions()) {
                            registry.register(definition);
                        }
                    }
                });
    }
}
