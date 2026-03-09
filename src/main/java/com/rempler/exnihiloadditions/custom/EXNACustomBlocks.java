package com.rempler.exnihiloadditions.custom;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.custom.blocks.CustomBarrelBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomCrucibleBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomFiredCrucibleBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomSieveBlock;
import com.rempler.exnihiloadditions.custom.items.CustomVariantBlockItem;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import novamachina.novacore.NovaCore;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.List;

public class EXNACustomBlocks {

    /** Wraps the NovaCore service provider to inject our custom block item factory. */
    private static final IServiceProvider CUSTOM_SERVICE = new IServiceProvider() {
        @Override public novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory blockEntityTypeFactory() {
            return NovaCore.SERVICE_PROVIDER.blockEntityTypeFactory();
        }
        @Override public novamachina.novacore.world.item.IBlockItemFactory blockItemFactory() {
            return (block, props) -> new CustomVariantBlockItem(block, props);
        }
        @Override public novamachina.novacore.world.level.block.IBlockFactory blockFactory() {
            return NovaCore.SERVICE_PROVIDER.blockFactory();
        }
    };

    public static final BlockRegistry BLOCKS = new BlockRegistry(ExNihiloAdditions.MODID, CUSTOM_SERVICE);

    private static final BlockBehaviour.Properties PROPS = BlockBehaviour.Properties.of()
            .noOcclusion()
            .strength(0.75F)
            .sound(SoundType.WOOD);

    private static final BlockBehaviour.Properties STONE_PROPS = BlockBehaviour.Properties.of()
            .noOcclusion()
            .strength(2.0F)
            .sound(SoundType.STONE);

    public static final BlockDefinition<CustomSieveBlock> CUSTOM_SIEVE =
            BLOCKS.burnableBlock("Custom Sieve", "custom_sieve", PROPS, CustomSieveBlock::new);

    public static final BlockDefinition<CustomBarrelBlock> CUSTOM_BARREL =
            BLOCKS.burnableBlock("Custom Barrel", "custom_barrel", PROPS, CustomBarrelBlock::new);

    public static final BlockDefinition<CustomCrucibleBlock> CUSTOM_CRUCIBLE =
            BLOCKS.burnableBlock("Custom Crucible", "custom_crucible", PROPS, CustomCrucibleBlock::new);

    public static final BlockDefinition<CustomFiredCrucibleBlock> CUSTOM_FIRED_CRUCIBLE =
            BLOCKS.burnableBlock("Custom Fired Crucible", "custom_fired_crucible", STONE_PROPS, CustomFiredCrucibleBlock::new);

    public static List<BlockDefinition<?>> getDefinitions() {
        return BLOCKS.getRegistry();
    }
}
