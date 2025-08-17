package com.rempler.exnihiloadditions.compat.tetra;

import com.mojang.serialization.Codec;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tetra.loot.EXACrookLootModifier;
import com.rempler.exnihiloadditions.compat.tetra.loot.EXAHammerLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EXATetraLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExNihiloAdditions.MODID);

    public static final RegistryObject<Codec<EXAHammerLootModifier>> HAMMER_LOOT_MODIFIER = LOOT_MODIFIERS.register("hammer_loot_modifier", EXAHammerLootModifier.CODEC);
    public static final RegistryObject<Codec<EXACrookLootModifier>> CROOK_LOOT_MODIFIER = LOOT_MODIFIERS.register("crook_loot_modifier", EXACrookLootModifier.CODEC);
}
