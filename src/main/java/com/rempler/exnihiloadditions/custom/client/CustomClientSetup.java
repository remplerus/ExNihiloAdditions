package com.rempler.exnihiloadditions.custom.client;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.custom.EXNACustomBlockEntities;
import com.rempler.exnihiloadditions.custom.EXNACustomBlocks;
import com.rempler.exnihiloadditions.custom.config.CustomVariantConfig;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.ArrayList;
import java.util.Map;

@EventBusSubscriber(modid = ExNihiloAdditions.MODID, value = Dist.CLIENT)
public class CustomClientSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                EXNACustomBlockEntities.CUSTOM_SIEVE.getType(), CustomSieveRenderer::new);
        event.registerBlockEntityRenderer(
                EXNACustomBlockEntities.CUSTOM_BARREL.getType(), CustomBarrelRenderer::new);
        event.registerBlockEntityRenderer(
                EXNACustomBlockEntities.CUSTOM_CRUCIBLE.getType(), CustomCrucibleRenderer::new);
        event.registerBlockEntityRenderer(
                EXNACustomBlockEntities.CUSTOM_FIRED_CRUCIBLE.getType(), CustomFiredCrucibleRenderer::new);
    }

    /**
     * Wraps the baked models for all four custom block types so that getQuads()
     * remaps UV coordinates from the placeholder texture to the per-variant texture.
     * Items use getOverrides().resolve() to pick up the correct variant texture too.
     */
    @SubscribeEvent
    public static void modifyBaking(ModelEvent.ModifyBakingResult event) {
        Map<ModelResourceLocation, BakedModel> models = event.getModels();

        // These match the "texture" value declared in the block model JSONs
        ResourceLocation barrelTex   = ExNihiloAdditions.rl("block/custom_barrel");
        ResourceLocation sieveTex    = ExNihiloAdditions.rl("block/custom_sieve");
        ResourceLocation crucibleTex = ExNihiloAdditions.rl("block/custom_crucible");

        new ArrayList<>(models.entrySet()).forEach(e -> {
            ModelResourceLocation mrl = e.getKey();
            ResourceLocation id = mrl.id();
            if (!id.getNamespace().equals(ExNihiloAdditions.MODID)) return;
            VariantBakedModelWrapper wrapped = switch (id.getPath()) {
                case "custom_barrel" ->
                        new VariantBakedModelWrapper(e.getValue(), barrelTex, CustomVariantConfig::barrelTexture);
                case "custom_sieve" ->
                        new VariantBakedModelWrapper(e.getValue(), sieveTex, CustomVariantConfig::sieveTexture);
                case "custom_crucible", "custom_fired_crucible" ->
                        new VariantBakedModelWrapper(e.getValue(), crucibleTex, CustomVariantConfig::crucibleTexture);
                default -> null;
            };
            if (wrapped == null) return;
            models.put(mrl, wrapped);
        });
    }
}
