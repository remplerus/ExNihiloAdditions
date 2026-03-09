package com.rempler.exnihiloadditions.custom.config;

import com.google.gson.annotations.SerializedName;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.resources.ResourceLocation;

/**
 * Defines one custom variant (sieve / barrel / crucible).
 * Loaded from config/exnihiloadditions/variants/variant_id.json
 *
 * Textures are resolved by convention from the variant id:
 *   exnihiloadditions:block/<id>_barrel
 *   exnihiloadditions:block/<id>_sieve
 *   exnihiloadditions:block/<id>_crucible
 * Provide the PNGs in a resource pack under assets/exnihiloadditions/textures/block/.
 * Missing PNG → falls back to the generic custom_barrel/sieve/crucible placeholder.
 *
 * Example JSON:
 * {
 *   "display_name": "Diorite",
 *   "has_sieve": true,
 *   "has_barrel": true,
 *   "has_crucible": true,
 *   "crucible_type": "fired"
 * }
 */
public record CustomVariantConfig(
    String id,
    @SerializedName("display_name") String displayName,
    @SerializedName("has_sieve") boolean hasSieve,
    @SerializedName("has_barrel") boolean hasBarrel,
    @SerializedName("has_crucible") boolean hasCrucible,
    @SerializedName("crucible_type") String crucibleType   // "wood" or "fired"
) {
    public boolean isFiredCrucible() { return "fired".equals(crucibleType); }

    public ResourceLocation barrelTexture()   { return ExNihiloAdditions.rl("block/" + id + "_barrel");   }
    public ResourceLocation sieveTexture()    { return ExNihiloAdditions.rl("block/" + id + "_sieve");    }
    public ResourceLocation crucibleTexture() { return ExNihiloAdditions.rl("block/" + id + "_crucible"); }
}
