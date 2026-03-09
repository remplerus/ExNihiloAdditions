package com.rempler.exnihiloadditions.custom;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.custom.items.CustomDollItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EXNACustomItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, ExNihiloAdditions.MODID);

    public static final DeferredHolder<Item, CustomDollItem> CUSTOM_DOLL =
            ITEMS.register("custom_doll", () -> new CustomDollItem(new Item.Properties().stacksTo(16)));
}
