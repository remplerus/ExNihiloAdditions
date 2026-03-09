package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.client.EXAClientSetup;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCItems;
import com.rempler.exnihiloadditions.compat.tfc.client.EXNATFCClientSetup;
import com.rempler.exnihiloadditions.custom.EXNACustomBlockEntities;
import com.rempler.exnihiloadditions.custom.EXNACustomBlocks;
import com.rempler.exnihiloadditions.custom.EXNACustomCapabilities;
import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import com.rempler.exnihiloadditions.custom.EXNACustomItems;
import com.rempler.exnihiloadditions.custom.EXNACommonEventHandlers;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
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
        // Load custom variant / doll configs early, before registration
        CustomVariantLoader.init();

        // Register DataComponents and Items via DeferredRegister
        EXNACustomComponents.DATA_COMPONENTS.register(eventBus);
        EXNACustomItems.ITEMS.register(eventBus);

        // Register capability and event handlers (avoids deprecated EventBusSubscriber.Bus)
        eventBus.addListener(EXNACustomCapabilities::registerCapabilities);
        NeoForge.EVENT_BUS.addListener(EXNACommonEventHandlers::onRightClickBlock);

        if (FMLEnvironment.dist.isClient()) {
            if (isTFCLoaded) {
                LOGGER.info("TFC is loaded, registering TFC compat");
                eventBus.addListener(EXNATFCClientSetup::init);
            }
            EXAClientSetup.register(eventBus);
        }


        eventBus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                // Custom blocks
                NeoforgeBlockRegistry blockRegistry = new NeoforgeBlockRegistry();
                for (BlockDefinition<?> definition : EXNACustomBlocks.getDefinitions()) {
                    blockRegistry.register(definition);
                }
                // TFC blocks
                if (isTFCLoaded) {
                    for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                        blockRegistry.register(definition);
                    }
                }
            }
            if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                // Custom block entities
                NeoforgeBlockEntityTypeRegistry beRegistry = new NeoforgeBlockEntityTypeRegistry();
                for (BlockEntityTypeDefinition<?> definition : EXNACustomBlockEntities.getDefinitions()) {
                    beRegistry.register(definition);
                }
                // TFC block entities
                if (isTFCLoaded) {
                    for (BlockEntityTypeDefinition<?> definition : EXNATFCBlockEntites.getDefinitions()) {
                        beRegistry.register(definition);
                    }
                }
            }
            if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                // Custom block items
                NeoforgeItemRegistry itemRegistry = new NeoforgeItemRegistry();
                for (BlockDefinition<?> definition : EXNACustomBlocks.getDefinitions()) {
                    itemRegistry.register(definition);
                }
                // TFC block/items
                if (isTFCLoaded) {
                    for (BlockDefinition<?> definition : EXNATFCBlocks.getDefinitions()) {
                        itemRegistry.register(definition);
                    }
                    for (ItemDefinition<?> definition : EXNATFCItems.getDefinitions()) {
                        itemRegistry.register(definition);
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
