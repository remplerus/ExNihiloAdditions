package com.rempler.exnihiloadditions.custom.blocks;

import com.rempler.exnihiloadditions.custom.EXNACustomBlockEntities;
import com.rempler.exnihiloadditions.custom.client.VariantModelProperties;
import com.rempler.exnihiloadditions.custom.config.CustomVariantConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.world.level.block.entity.WoodCrucibleBlockEntity;

public class CustomCrucibleBlockEntity extends WoodCrucibleBlockEntity implements IVariantHolder {

    private String variantId = "";

    public CustomCrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(EXNACustomBlockEntities.CUSTOM_CRUCIBLE.getType(), pos, state);
    }

    public CustomCrucibleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super((BlockEntityType<? extends WoodCrucibleBlockEntity>) type, pos, state);
    }

    @Override
    public String getVariantId() { return variantId; }

    @Override
    public void setVariantId(String variantId) {
        this.variantId = variantId;
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("variant", variantId);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        variantId = tag.contains("variant") ? tag.getString("variant") : "";
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putString("variant", variantId);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        requestModelDataUpdate();
    }

    @Override
    public void onDataPacket(net.minecraft.network.Connection net,
                             ClientboundBlockEntityDataPacket pkt,
                             HolderLookup.Provider registries) {
        super.onDataPacket(net, pkt, registries);
        requestModelDataUpdate();
    }

    @Override
    public ModelData getModelData() {
        if (variantId.isEmpty()) return ModelData.EMPTY;
        CustomVariantConfig cfg = CustomVariantLoader.getVariant(variantId);
        if (cfg == null) return ModelData.EMPTY;
        return ModelData.builder()
                .with(VariantModelProperties.TEXTURE, cfg.crucibleTexture())
                .build();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
