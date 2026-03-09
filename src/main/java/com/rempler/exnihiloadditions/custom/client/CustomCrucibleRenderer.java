package com.rempler.exnihiloadditions.custom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rempler.exnihiloadditions.custom.blocks.CustomCrucibleBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.client.util.LiquidBlockVertexConsumer;
import novamachina.novacore.client.renderer.blockentity.AbstractBlockEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCrucibleRenderer extends AbstractBlockEntityRenderer<CustomCrucibleBlockEntity> {

    private static final Logger log = LoggerFactory.getLogger(CustomCrucibleRenderer.class);

    public CustomCrucibleRenderer(BlockEntityRendererProvider.Context ctx) {
        super();
    }

    @Override
    public void render(CustomCrucibleBlockEntity be, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        renderFluid(poseStack, buffer, be);
        renderSolid(be, poseStack, buffer, combinedLight, combinedOverlay);
    }

    private void renderFluid(PoseStack poseStack, MultiBufferSource buffer,
                             CustomCrucibleBlockEntity be) {
        if (be.getFluidAmount() > 0) {
            BlockState state = be.getFluid().defaultFluidState().createLegacyBlock();
            poseStack.pushPose();

            final float fillAmount = Math.min(be.getFluidProportion(), 1.0F);

            poseStack.translate(0.125, 0.1875, 0.125);
            poseStack.scale(0.75F, fillAmount, 0.75F);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            blockRenderer.renderLiquid(
                    be.getBlockPos(),
                    be.getLevel(),
                    new LiquidBlockVertexConsumer(
                            buffer.getBuffer(ItemBlockRenderTypes.getRenderLayer(
                                    be.getFluid().defaultFluidState())),
                            poseStack,
                            be.getBlockPos()),
                    state,
                    be.getFluid().defaultFluidState());
            poseStack.popPose();
        }
    }

    private void renderSolid(CustomCrucibleBlockEntity be, PoseStack poseStack,
                             MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (be.getSolidAmount() > 0) {
            BlockState state = null;
            if (be.getCurrentItem().getItem() instanceof BlockItem blockItem) {
                state = blockItem.getBlock().defaultBlockState();
            }
            poseStack.pushPose();

            final float fillAmount = Math.max(be.getSolidProportion(), 0.0F) - 0.1875F;

            poseStack.translate(0.125, 0.1875, 0.125);
            poseStack.scale(0.75F, fillAmount, 0.75F);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            if (state != null) {
                blockRenderer.renderSingleBlock(state, poseStack, buffer,
                        combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.cutoutMipped());
            } else {
                log.warn("BlockState was null for crucible solid content");
            }
            poseStack.popPose();
        }
    }
}
