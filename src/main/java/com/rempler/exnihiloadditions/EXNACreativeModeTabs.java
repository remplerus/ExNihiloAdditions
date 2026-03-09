package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCItems;
import com.rempler.exnihiloadditions.custom.EXNACustomBlocks;
import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import com.rempler.exnihiloadditions.custom.EXNACustomItems;
import com.rempler.exnihiloadditions.custom.config.CustomDollConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.core.registries.CreativeModeTabRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

import javax.annotation.Nonnull;
import java.util.List;

public class EXNACreativeModeTabs {

    private static final CreativeModeTabRegistry CREATIVE_MODE_TABS = new CreativeModeTabRegistry(ExNihiloAdditions.MODID);

    public static List<CreativeModeTabDefinition> getDefinitions() {
        return CREATIVE_MODE_TABS.getRegistry();
    }

    @Nonnull
    public static final CreativeModeTabDefinition EXN = CREATIVE_MODE_TABS.creativeModeTab("creative_tab",
            CreativeModeTab.builder()
                    .icon(EXNItems.HAMMER_NETHERITE::itemStack)
                    .title(Component.literal("Ex Nihilo: Additions"))
                    .displayItems((parameters, output) -> {
                        // One stack per variant per applicable block type
                        for (CustomVariantConfig config : CustomVariantLoader.getAllVariants()) {
                            if (config.hasSieve()) {
                                ItemStack stack = EXNACustomBlocks.CUSTOM_SIEVE.itemStack();
                                stack.set(EXNACustomComponents.VARIANT_ID.get(), config.id());
                                stack.set(DataComponents.CUSTOM_NAME,
                                        Component.literal(config.displayName() + " Sieve")
                                                .withStyle(s -> s.withItalic(false)));
                                output.accept(stack);
                            }
                            if (config.hasBarrel()) {
                                ItemStack stack = EXNACustomBlocks.CUSTOM_BARREL.itemStack();
                                stack.set(EXNACustomComponents.VARIANT_ID.get(), config.id());
                                stack.set(DataComponents.CUSTOM_NAME,
                                        Component.literal(config.displayName() + " Barrel")
                                                .withStyle(s -> s.withItalic(false)));
                                output.accept(stack);
                            }
                            if (config.hasCrucible()) {
                                ItemStack stack = config.isFiredCrucible()
                                        ? EXNACustomBlocks.CUSTOM_FIRED_CRUCIBLE.itemStack()
                                        : EXNACustomBlocks.CUSTOM_CRUCIBLE.itemStack();
                                stack.set(EXNACustomComponents.VARIANT_ID.get(), config.id());
                                stack.set(DataComponents.CUSTOM_NAME,
                                        Component.literal(config.displayName() + " Crucible")
                                                .withStyle(s -> s.withItalic(false)));
                                output.accept(stack);
                            }
                        }

                        // One doll item per loaded doll config
                        for (CustomDollConfig config : CustomVariantLoader.getAllDolls()) {
                            ItemStack doll = new ItemStack(EXNACustomItems.CUSTOM_DOLL.get());
                            doll.set(EXNACustomComponents.VARIANT_ID.get(), config.id());
                            output.accept(doll);
                        }

                        // TFC compat
                        if (ModList.get().isLoaded("tfc")) {
                            EXNATFCBlocks.getDefinitions().forEach(output::accept);
                            EXNATFCItems.getDefinitions().forEach(output::accept);
                        }
                    }).build());
}
