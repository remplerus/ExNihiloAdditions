package com.rempler.exnihiloadditions.custom.blocks;

/** Implemented by all custom block entities to expose their variant id. */
public interface IVariantHolder {
    String getVariantId();
    void setVariantId(String variantId);
}
