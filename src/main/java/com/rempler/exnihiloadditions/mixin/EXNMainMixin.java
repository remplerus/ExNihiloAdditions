package com.rempler.exnihiloadditions.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.core.registries.InitBlockEntityTypes;
import novamachina.exnihilosequentia.core.registries.InitBlocks;
import novamachina.exnihilosequentia.core.registries.InitCreativeTabs;
import novamachina.exnihilosequentia.core.registries.InitFluidTypes;
import novamachina.exnihilosequentia.core.registries.InitFluids;
import novamachina.exnihilosequentia.core.registries.InitItems;
import novamachina.exnihilosequentia.core.registries.InitLootModifiers;
import novamachina.exnihilosequentia.core.registries.InitRecipeSerializers;
import novamachina.exnihilosequentia.core.registries.InitRecipeTypes;
import novamachina.exnihilosequentia.core.registries.InitSoundEvents;
import novamachina.exnihilosequentia.core.registries.InitStats;
import novamachina.novacore.bootstrap.core.registries.NeoforgeBlockEntityTypeRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeBlockRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeCreativeModeTabRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeFluidRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeFluidTypeRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeItemRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeLootModifierRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeRecipeSerializerRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeRecipeTypeRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeSoundEventRegistry;
import novamachina.novacore.bootstrap.core.registries.NeoforgeStatRegistry;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ExNihiloSequentia.class)
public class EXNMainMixin {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EXNMainMixin.class);
    @Inject(method = "<init>(Lnet/neoforged/fml/ModContainer;Lnet/neoforged/bus/api/IEventBus;)V", at = @At("HEAD"), remap = false)
    private static void exna$init(ModContainer container, IEventBus modEventBus, CallbackInfo ci) {
        container.registerConfig(ModConfig.Type.STARTUP, Config.COMMON_CONFIG);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        log.debug("Starting Ex Nihilo: Sequentia");
        modEventBus.addListener(ClientSetup::init);
        modEventBus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                InitBlocks.init(new NeoforgeBlockRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                InitBlockEntityTypes.init(new NeoforgeBlockEntityTypeRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.FLUID.key())) {
                InitFluidTypes.init(new NeoforgeFluidTypeRegistry());
                InitFluids.init(new NeoforgeFluidRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                InitItems.init(new NeoforgeItemRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_SERIALIZER.key())) {
                InitRecipeSerializers.init(new NeoforgeRecipeSerializerRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_TYPE.key())) {
                InitRecipeTypes.init(new NeoforgeRecipeTypeRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.SOUND_EVENT.key())) {
                InitSoundEvents.init(new NeoforgeSoundEventRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.STAT_TYPE.key())) {
                InitStats.init(new NeoforgeStatRegistry());
            }

            if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
                InitCreativeTabs.init(new NeoforgeCreativeModeTabRegistry());
            }

            if (event.getRegistryKey().equals(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS.key())) {
                InitLootModifiers.init(new NeoforgeLootModifierRegistry());
            }
        });
    }
}
