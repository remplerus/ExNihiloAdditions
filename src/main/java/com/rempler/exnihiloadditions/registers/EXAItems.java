package com.rempler.exnihiloadditions.registers;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

import java.util.List;

public class EXAItems {
    public static final ItemRegistry ITEMS = new ItemRegistry(ExNihiloAdditions.ModIds.MODID);

    public static List<ItemDefinition<?>> getDefinitions() {
        return ITEMS.getRegistry();
    }
}
