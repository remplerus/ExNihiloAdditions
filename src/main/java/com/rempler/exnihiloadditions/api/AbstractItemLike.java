package com.rempler.exnihiloadditions.api;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public abstract class AbstractItemLike implements ItemLike {
    public final SoundType soundType;
    public final float strength;
    public final boolean requiresCorrectTool;
    public final String requiredModId;

    ItemDefinition<?> item;
    BlockDefinition<?> block;

    public AbstractItemLike(SoundType soundType, float strength, boolean requiresCorrectTool, String requiredModId) {
        this.soundType = soundType;
        this.strength = strength;
        this.requiresCorrectTool = requiresCorrectTool;
        this.requiredModId = requiredModId;
    }

    protected abstract Block createBlock();

    protected BlockItem createBlockItem(Block block) {
        return new BlockItem(block, new BlockItem.Properties());
    }

    protected BlockBehaviour.Properties properties() {
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of().sound(this.soundType).strength(this.strength);
        if (this.requiresCorrectTool) props.requiresCorrectToolForDrops();
        return props;
    }

    public Item getItem() {
        return this.item.asItem();
    }

    public Block getBlock() {
        return this.block.block();
    }

    @Override
    public Item asItem() {
        return getItem();
    }
}
