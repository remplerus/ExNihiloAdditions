package com.rempler.exnihiloadditions.registers;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

import java.util.List;

public class EXAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM.key(), ExNihiloAdditions.ModIds.MODID);
}
