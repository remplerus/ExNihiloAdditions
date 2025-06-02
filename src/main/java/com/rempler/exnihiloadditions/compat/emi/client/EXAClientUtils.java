package com.rempler.exnihiloadditions.compat.emi.client;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Axis;
import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;

public class EXAClientUtils {
    private static final FluidState EMPTY = Fluids.EMPTY.defaultFluidState();
    private static final BlockState AIR = Blocks.AIR.defaultBlockState();

    // From https://github.com/The-Aether-Team/Nitrogen/blob/1.20.1-develop/src/main/java/com/aetherteam/nitrogen/integration/jei/BlockStateRenderer.java
    private static final Vector3f L1 = new Vector3f(0.4F, 0.0F, 1.0F).normalize();
    private static final Vector3f L2 = new Vector3f(-0.4F, 1.0F, -0.2F).normalize();

    public static void renderCompostBarrel(GuiGraphics graphics, int x, int y) {
        RenderSystem.enableDepthTest();

        Minecraft mc = Minecraft.getInstance();
        BakedModel model = mc.getModelManager().getModel(ModelResourceLocation.inventory(EXNEMIPlugin.COMPOSTING_MODEL));
        ItemStack stack = new ItemStack(EXNBlocks.OAK_BARREL);
        renderCustomItem(mc, graphics, model, stack, x, y);

        RenderSystem.disableDepthTest();
    }

    private static void renderCustomItem(Minecraft mc, GuiGraphics graphics, BakedModel model, ItemStack stack, int x, int y) {
        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(x + 8, y + 8, 150);

        try {
            pose.mulPose((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
            pose.scale(16.0F, 16.0F, 16.0F);
            boolean flag = !model.usesBlockLight();
            if (flag) {
                Lighting.setupForFlatItems();
            }

            mc.getItemRenderer().render(stack, ItemDisplayContext.GUI, false, pose, graphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, model);
            graphics.flush();
            if (flag) {
                Lighting.setupFor3DItems();
            }
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering item");
            CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
            crashreportcategory.setDetail("Item Type", () -> String.valueOf(stack.getItem()));
            crashreportcategory.setDetail("Registry Name", () -> BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());
            throw new ReportedException(crashreport);
        }

        pose.popPose();

        RenderSystem.disableBlend();
    }

    // https://github.com/way2muchnoise/JustEnoughResources/blob/89ee40ff068c8d6eb6ab103f76381445691cffc9/Common/src/main/java/jeresources/util/RenderHelper.java#L100
    public static void renderBlock(GuiGraphics guiGraphics, BlockState state, float x, float y, float z, float scale) {
        PoseStack poseStack = guiGraphics.pose();

        poseStack.translate(x, y, z);
        poseStack.scale(-scale, -scale, -scale);
        poseStack.translate(-0.5F, -0.5F, 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(-30F));
        poseStack.translate(0.5F, 0, -0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(45f));
        poseStack.translate(-0.5F, 0, 0.5F);

        poseStack.pushPose();
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        poseStack.translate(0, 0, -1);

        FluidState fluidState = state.getFluidState();

        if (fluidState.isEmpty()) {
            MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();

            RenderSystem.setupGui3DDiffuseLighting(L1, L2);
            if (state.is(EXNBlocks.INFESTED_LEAVES.block())) {
                var blockRenderer = Minecraft.getInstance().getBlockRenderer();
                var bakedmodel = blockRenderer.getBlockModel(state);

                for (var renderType : bakedmodel.getRenderTypes(state, RandomSource.create(42), ModelData.EMPTY)) {
                    blockRenderer.getModelRenderer().renderModel(poseStack.last(), buffers.getBuffer(RenderTypeHelper.getEntityRenderType(renderType, false)), state, bakedmodel, 1f, 1f, 1f, 15728880, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, renderType);
                }
            } else {
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffers, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
            }

            buffers.endBatch();
        } else {
            RenderType renderType = ItemBlockRenderTypes.getRenderLayer(fluidState);
            Matrix4fStack modelView = RenderSystem.getModelViewStack();
            Tesselator tesselator = Tesselator.getInstance();
            renderType.setupRenderState();
            modelView.pushMatrix();
            modelView.mul(poseStack.last().pose());
            RenderSystem.applyModelViewMatrix();

            BufferBuilder builder = tesselator.begin(renderType.mode(), renderType.format());

            Dummy.tempState = state;
            Dummy.tempFluid = fluidState;
            Minecraft.getInstance().getBlockRenderer().renderLiquid(BlockPos.ZERO, Dummy.INSTANCE, builder, state, state.getFluidState());
            Dummy.tempFluid = EMPTY;
            Dummy.tempState = AIR;

            MeshData build = builder.build();
            if (build != null) {
                BufferUploader.drawWithShader(build);
            }

            renderType.clearRenderState();
            modelView.popMatrix();
            RenderSystem.applyModelViewMatrix();
        }

        poseStack.popPose();
    }

    private enum Dummy implements BlockAndTintGetter {
        INSTANCE;

        private static BlockState tempState = AIR;
        private static FluidState tempFluid = EMPTY;

        @Override
        public float getShade(Direction pDirection, boolean pShade) {
            return 1;
        }

        @Override
        public LevelLightEngine getLightEngine() {
            return Minecraft.getInstance().level.getLightEngine();
        }

        @Override
        public int getBlockTint(BlockPos pBlockPos, ColorResolver pColorResolver) {
            return 0;
        }

        @Override
        public int getBrightness(LightLayer pLightType, BlockPos pBlockPos) {
            return 15;
        }

        @Override
        public int getRawBrightness(BlockPos pBlockPos, int pAmount) {
            return 15;
        }

        @Nullable
        @Override
        public BlockEntity getBlockEntity(BlockPos pPos) {
            return null;
        }

        @Override
        public BlockState getBlockState(BlockPos pos) {
            return pos.equals(BlockPos.ZERO) ? tempState : AIR;
        }

        @Override
        public FluidState getFluidState(BlockPos pos) {
            return pos.equals(BlockPos.ZERO) ? tempFluid : EMPTY;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public int getMinBuildHeight() {
            return 0;
        }
    }
}
