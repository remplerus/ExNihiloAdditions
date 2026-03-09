package com.rempler.exnihiloadditions.custom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import com.rempler.exnihiloadditions.custom.config.CustomVariantConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class VariantBakedModelWrapper extends BakedModelWrapper<BakedModel> {

    /** The texture the model uses by default (e.g. exnihiloadditions:block/custom_barrel). */
    private final ResourceLocation defaultTextureRL;

    /** Extracts the variant-specific texture RL from the config (barrel vs sieve vs crucible). */
    private final Function<CustomVariantConfig, ResourceLocation> textureExtractor;

    public VariantBakedModelWrapper(BakedModel original, ResourceLocation defaultTextureRL,
                                    Function<CustomVariantConfig, ResourceLocation> textureExtractor) {
        super(original);
        this.defaultTextureRL = defaultTextureRL;
        this.textureExtractor = textureExtractor;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
                                    RandomSource rand, ModelData data,
                                    @Nullable RenderType renderType) {
        ResourceLocation variantTex = data.get(VariantModelProperties.TEXTURE);
        List<BakedQuad> original = super.getQuads(state, side, rand, data, renderType);

        if (variantTex == null) {
            return original;
        }

        TextureAtlas atlas = Minecraft.getInstance().getModelManager()
                .getAtlas(TextureAtlas.LOCATION_BLOCKS);

        // Try the variant-specific texture first; fall back to the base placeholder texture
        TextureAtlasSprite newSprite = atlas.getSprite(variantTex);
        if (MissingTextureAtlasSprite.getLocation().equals(newSprite.contents().name())) {
            newSprite = atlas.getSprite(defaultTextureRL);
        }
        // If even the base texture is missing, return original quads unchanged
        if (MissingTextureAtlasSprite.getLocation().equals(newSprite.contents().name())) {
            return original;
        }

        final TextureAtlasSprite finalSprite = newSprite;
        List<BakedQuad> result = new ArrayList<>(original.size());
        for (BakedQuad quad : original) {
            TextureAtlasSprite quadSprite = quad.getSprite();
            if (defaultTextureRL.equals(quadSprite.contents().name())) {
                // tintIndex=0 so the registered BlockColor/ItemColor tint is applied
                result.add(remapQuad(quad, quadSprite, finalSprite));
            } else {
                result.add(quad);
            }
        }
        return result;
    }

    @Override
    public TextureAtlasSprite getParticleIcon(ModelData data) {
        ResourceLocation variantTex = data.get(VariantModelProperties.TEXTURE);
        if (variantTex == null) return super.getParticleIcon(data);
        TextureAtlas atlas = Minecraft.getInstance().getModelManager()
                .getAtlas(TextureAtlas.LOCATION_BLOCKS);
        TextureAtlasSprite sprite = atlas.getSprite(variantTex);
        if (MissingTextureAtlasSprite.getLocation().equals(sprite.contents().name())) {
            sprite = atlas.getSprite(defaultTextureRL);
        }
        return sprite;
    }

    @Override
    public BakedModel applyTransform(ItemDisplayContext ctx, PoseStack poseStack, boolean leftHand) {
        // Apply the display transforms from the wrapped model, but return THIS wrapper so
        // ItemRenderer.getRenderPasses() is called on our instance, not the original model.
        originalModel.applyTransform(ctx, poseStack, leftHand);
        return this;
    }

    @Override
    public List<BakedModel> getRenderPasses(ItemStack stack, boolean fabulous) {
        return List.of(this);
    }

    @Override
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @Override
            public @Nullable BakedModel resolve(BakedModel model, ItemStack stack,
                                               @Nullable ClientLevel level,
                                               @Nullable LivingEntity entity, int seed) {
                String variantId = stack.get(EXNACustomComponents.VARIANT_ID.get());
                if (variantId == null || variantId.isEmpty()) return model;
                CustomVariantConfig cfg = CustomVariantLoader.getVariant(variantId);
                if (cfg == null) return model;

                ResourceLocation tex = textureExtractor.apply(cfg);
                ModelData itemData = ModelData.builder()
                        .with(VariantModelProperties.TEXTURE, tex)
                        .build();

                // Return a wrapper that injects the variant ModelData into getQuads(),
                // and keeps itself as the active model through applyTransform/getRenderPasses.
                return new BakedModelWrapper<BakedModel>(VariantBakedModelWrapper.this) {
                    // ItemRenderer.renderModelLists() calls the vanilla 3-arg overload
                    @Override
                    public List<BakedQuad> getQuads(@Nullable BlockState s, @Nullable Direction d, RandomSource r) {
                        return VariantBakedModelWrapper.this.getQuads(s, d, r, itemData, null);
                    }

                    // Chunk renderer uses the 5-arg NeoForge overload
                    @Override
                    public List<BakedQuad> getQuads(@Nullable BlockState s, @Nullable Direction d,
                                                    RandomSource r, ModelData ignored,
                                                    @Nullable RenderType rt) {
                        return VariantBakedModelWrapper.this.getQuads(s, d, r, itemData, rt);
                    }

                    @Override
                    public TextureAtlasSprite getParticleIcon(ModelData ignored) {
                        return VariantBakedModelWrapper.this.getParticleIcon(itemData);
                    }

                    @Override
                    public BakedModel applyTransform(ItemDisplayContext ctx, PoseStack poseStack, boolean leftHand) {
                        originalModel.applyTransform(ctx, poseStack, leftHand);
                        return this; // keep THIS anonymous instance active
                    }

                    @Override
                    public List<BakedModel> getRenderPasses(ItemStack s, boolean fab) {
                        return List.of(this); // render THIS anonymous instance's quads
                    }
                };
            }
        };
    }

    private static BakedQuad remapQuad(BakedQuad quad,
                                       TextureAtlasSprite oldSprite,
                                       TextureAtlasSprite newSprite) {
        int[] src = quad.getVertices();
        int[] dst = Arrays.copyOf(src, src.length);
        int stride = 8;
        for (int i = 0; i < 4; i++) {
            int base = i * stride;
            float u = Float.intBitsToFloat(dst[base + 4]);
            float v = Float.intBitsToFloat(dst[base + 5]);
            float localU = (u - oldSprite.getU0()) / (oldSprite.getU1() - oldSprite.getU0());
            float localV = (v - oldSprite.getV0()) / (oldSprite.getV1() - oldSprite.getV0());
            dst[base + 4] = Float.floatToRawIntBits(newSprite.getU(localU));
            dst[base + 5] = Float.floatToRawIntBits(newSprite.getV(localV));
        }
        // tintIndex=0 enables BlockColor/ItemColor tinting; original quads have -1 (no tint)
        return new BakedQuad(dst, 0, quad.getDirection(), newSprite, quad.isShade());
    }
}
