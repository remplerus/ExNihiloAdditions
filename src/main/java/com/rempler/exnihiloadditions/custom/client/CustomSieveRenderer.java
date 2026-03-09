package com.rempler.exnihiloadditions.custom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.rempler.exnihiloadditions.custom.blocks.CustomSieveBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.novacore.client.renderer.blockentity.AbstractBlockEntityRenderer;

public class CustomSieveRenderer extends AbstractBlockEntityRenderer<CustomSieveBlockEntity> {

    public CustomSieveRenderer(BlockEntityRendererProvider.Context ctx) {
        super();
    }

    @Override
    public void render(CustomSieveBlockEntity be, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0.53, 0.53, -0.6875);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderStatic(
                be.getMeshStack(),
                ItemDisplayContext.FIXED,
                combinedLight,
                combinedOverlay,
                poseStack,
                buffer,
                Minecraft.getInstance().level,
                0);
        poseStack.popPose();

        if (be.getBlockStack() != ItemStack.EMPTY) {
            BlockState state = getStateFromItemStack(be.getBlockStack());
            poseStack.pushPose();

            poseStack.translate(0.01, 0.819, 0.01);
            poseStack.scale(0.98F, 0.18F - be.getProgress() * 0.16F, 0.98F);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            blockRenderer.renderSingleBlock(state, poseStack, buffer,
                    combinedLight, combinedOverlay, ModelData.EMPTY, RenderType.cutoutMipped());
            poseStack.popPose();
        }
    }
}
