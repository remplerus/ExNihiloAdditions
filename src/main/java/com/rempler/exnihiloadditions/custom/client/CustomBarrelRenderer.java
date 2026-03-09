package com.rempler.exnihiloadditions.custom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rempler.exnihiloadditions.custom.blocks.CustomBarrelBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.client.util.LiquidBlockVertexConsumer;
import novamachina.novacore.client.renderer.blockentity.AbstractBlockEntityRenderer;

public class CustomBarrelRenderer extends AbstractBlockEntityRenderer<CustomBarrelBlockEntity> {

    public CustomBarrelRenderer(BlockEntityRendererProvider.Context ctx) {
        super();
    }

    @Override
    public void render(CustomBarrelBlockEntity be, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        renderFluid(poseStack, buffer, be);
        renderInventory(poseStack, buffer, combinedLight, combinedOverlay, be);
        renderSolid(be, poseStack, buffer, combinedLight, combinedOverlay);
    }

    private void renderFluid(PoseStack poseStack, MultiBufferSource buffer,
                             CustomBarrelBlockEntity be) {
        if (be.getFluidAmount() > 0) {
            BlockState state = be.getFluid().defaultFluidState().createLegacyBlock();
            poseStack.pushPose();

            final float fillAmount = Math.min(be.getFluidProportion(), 1.0F);

            poseStack.translate(0.125, 0.0625, 0.125);
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

    private void renderInventory(PoseStack poseStack, MultiBufferSource buffer,
                                 int combinedLight, int combinedOverlay,
                                 CustomBarrelBlockEntity be) {
        if (be.getInventoryBlock() != ItemStack.EMPTY) {
            BlockState state = getStateFromItemStack(be.getInventoryBlock());
            poseStack.pushPose();

            poseStack.translate(0.125, 0.0625, 0.125);
            poseStack.scale(0.75F, 0.9375F, 0.75F);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            blockRenderer.renderSingleBlock(state, poseStack, buffer,
                    combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.cutoutMipped());
            poseStack.popPose();
        }
    }

    private void renderSolid(CustomBarrelBlockEntity be, PoseStack poseStack,
                             MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (be.getSolidAmount() > 0) {
            BlockState state = Blocks.OAK_LEAVES.defaultBlockState();
            poseStack.pushPose();

            final float fillAmount = Math.min(be.getSolidProportion(), 1.0F) - 0.0625F;

            poseStack.translate(0.125, 0.0625, 0.125);
            poseStack.scale(0.75F, fillAmount, 0.75F);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            blockRenderer.renderSingleBlock(state, poseStack, buffer,
                    combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.cutoutMipped());
            poseStack.popPose();
        }
    }
}
