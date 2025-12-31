package com.rempler.exnihiloadditions.compat.thermal;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import novamachina.exnihilosequentia.world.item.DollItem;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.ResourceItem;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

import java.util.List;

public class EXAThermalItems {
    public static ItemRegistry ITEMS = new ItemRegistry(ExNihiloAdditions.MODID);
    public static final ItemDefinition<DollItem> BASALZ_DOLL = ITEMS.item("Basalz Doll", "basalz_doll",
            () -> new DollItem("thermal", "basalz", "minecraft", "lava", 1.0D, "tooltip.doll.basalz"), ItemDefinition.ItemType.OTHER);
    public static final ItemDefinition<DollItem> BLITZ_DOLL = ITEMS.item("Blitz Doll", "blitz_doll",
            () -> new DollItem("thermal", "blitz", "minecraft", "lava", 1.0D, "tooltip.doll.blitz"), ItemDefinition.ItemType.OTHER);
    public static final ItemDefinition<DollItem> BLIZZ_DOLL = ITEMS.item("Blizz Doll", "blizz_doll",
            () -> new DollItem("thermal", "blizz", "minecraft", "water", 1.0D, "tooltip.doll.blizz"), ItemDefinition.ItemType.OTHER);
    public static final ItemDefinition<ResourceItem> OBSIDIAN_DUST = ITEMS.item("Obsidian Dust", "obsidian_dust",
            () -> new ResourceItem("obsidian_dust"), ItemDefinition.ItemType.OTHER);

    public static List<ItemDefinition<?>> getDefinitions() {
        return ITEMS.getRegistry();
    }

    public static void enableOres() {
        EXNItems.SILVER.setEnabled(true);
        EXNItems.TIN.setEnabled(true);
        EXNItems.LEAD.setEnabled(true);
        EXNItems.NICKEL.setEnabled(true);
    }
}
