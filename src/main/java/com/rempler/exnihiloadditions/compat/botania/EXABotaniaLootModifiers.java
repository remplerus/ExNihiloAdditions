package com.rempler.exnihiloadditions.compat.botania;

import com.mojang.serialization.Codec;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.loot.SaplingModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EXABotaniaLootModifiers {
    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExNihiloAdditions.MODID);
    public static RegistryObject<Codec<SaplingModifier>> SAPLING_MODIFIER = LOOT_MODIFIERS.register("sapling_modifier", () -> SaplingModifier.CODEC);
}
