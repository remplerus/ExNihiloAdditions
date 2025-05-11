package com.rempler.exnihiloadditions.compat.tfc;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

import java.util.List;

public class EXNATFCItems {
    public static ItemRegistry ITEMS = new ItemRegistry(ExNihiloAdditions.MODID);

    public static List<ItemDefinition<?>> getDefinitions() {
        return ITEMS.getRegistry();
    }
}
