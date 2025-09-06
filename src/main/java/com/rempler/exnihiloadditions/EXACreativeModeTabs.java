package com.rempler.exnihiloadditions;

import com.rempler.exnihiloadditions.compat.botania.EXABotaniaBlocks;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.ModList;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.core.registries.CreativeModeTabRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

import javax.annotation.Nonnull;
import java.util.List;

public class EXACreativeModeTabs {

    private static final CreativeModeTabRegistry CREATIVE_MODE_TABS = new CreativeModeTabRegistry(ExNihiloAdditions.MODID);

    public static List<CreativeModeTabDefinition> getDefinitions() {
        return CREATIVE_MODE_TABS.getRegistry();
    }

    @Nonnull
    public static final CreativeModeTabDefinition EXN = CREATIVE_MODE_TABS.creativeModeTab("creative_tab", CreativeModeTab.builder().icon(EXNItems.HAMMER_NETHERITE::itemStack).title(Component.literal("Ex Nihilo: Additions")).displayItems((parameters, output) -> {
        if (ExNihiloAdditions.isTFCLoaded) {
            EXATFCBlocks.getDefinitions().forEach(output::accept);
            EXATFCItems.getDefinitions().forEach(output::accept);
        }
        if (ExNihiloAdditions.isBotaniaLoaded) {
            EXABotaniaItems.getDefinitions().forEach(output::accept);
            EXABotaniaBlocks.getDefinitions().forEach(output::accept);
        }
    }).build());
}
