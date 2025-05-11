package com.rempler.exnihiloadditions.api;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.world.item.Item;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

public class EXAItems {
    public static final ItemRegistry ITEMS = new ItemRegistry(ExNihiloAdditions.MODID);

    public static ItemDefinition<?> register(String id, Item item) {
        return ITEMS.register(id, item);
    }

    public static ItemDefinition<?> register(String id, Item item, String requiredModId) {
        return ITEMS.register(id, item, requiredModId);
    }
}
