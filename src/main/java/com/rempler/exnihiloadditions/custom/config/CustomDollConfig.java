package com.rempler.exnihiloadditions.custom.config;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

/**
 * Defines one custom doll recipe.
 * Loaded from config/exnihiloadditions/dolls/**‌/*.json
 *
 * Example JSON:
 * {
 *   "display_name": "Bee Doll",
 *   "fluid": "exnihilosequentia:witch_water",
 *   "entity": "minecraft:bee",
 *   "ticks": 1200,
 *   "texture": "exnihiloadditions:item/bee_doll"
 * }
 */
public record CustomDollConfig(
    String id,
    @SerializedName("display_name") String displayName,
    ResourceLocation fluid,
    ResourceLocation entity,
    int ticks,
    ResourceLocation texture
) {}