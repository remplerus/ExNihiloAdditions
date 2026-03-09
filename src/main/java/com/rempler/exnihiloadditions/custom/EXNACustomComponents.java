package com.rempler.exnihiloadditions.custom;

import com.mojang.serialization.Codec;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EXNACustomComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ExNihiloAdditions.MODID);

    /** Stores the variant id (e.g. "bloodwood") or doll id on an ItemStack. */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> VARIANT_ID =
            DATA_COMPONENTS.register("variant_id", () -> DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                    .build());
}
