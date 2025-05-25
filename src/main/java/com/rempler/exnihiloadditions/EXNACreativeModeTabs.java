package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.fml.ModList;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.core.registries.CreativeModeTabRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

import javax.annotation.Nonnull;
import java.util.List;

public class EXNACreativeModeTabs {

    private static final CreativeModeTabRegistry CREATIVE_MODE_TABS = new CreativeModeTabRegistry(ExNihiloAdditions.ModIds.MODID);

    public static List<CreativeModeTabDefinition> getDefinitions() {
        return CREATIVE_MODE_TABS.getRegistry();
    }

    @Nonnull
    public static final CreativeModeTabDefinition EXN = CREATIVE_MODE_TABS.creativeModeTab("creative_tab", CreativeModeTab.builder().icon(EXNItems.HAMMER_NETHERITE::itemStack).title(Component.literal("Ex Nihilo: Additions")).displayItems((parameters, output) -> {
        if (ModList.get().isLoaded("tfc")) {
            EXNATFCBlocks.getDefinitions().forEach(output::accept);
            EXNATFCItems.getDefinitions().forEach(output::accept);
        }
    }).build());
}
