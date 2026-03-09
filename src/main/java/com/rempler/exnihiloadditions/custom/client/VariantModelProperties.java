package com.rempler.exnihiloadditions.custom.client;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public final class VariantModelProperties {
    /** The convention-based texture RL for this block type + variant. */
    public static final ModelProperty<ResourceLocation> TEXTURE = new ModelProperty<>();

    private VariantModelProperties() {}
}
