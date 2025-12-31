package com.rempler.exnihiloadditions.compat.tfc;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.blocks.EXATFCBarrelBlock;
import com.rempler.exnihiloadditions.compat.tfc.blocks.EXATFCCrucibleBlock;
import com.rempler.exnihiloadditions.compat.tfc.blocks.EXATFCSieveBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.Util;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

import static com.rempler.exnihiloadditions.EXABlocksHelper.titleCase;

public class EXATFCBlocks {
    public static BlockRegistry BLOCKS = new BlockRegistry(ExNihiloAdditions.MODID);

    public static final Map<Wood, BlockDefinition<CrucibleBlock>> TFC_CRUCIBLES = Util.make(() -> {
        ImmutableMap.Builder<Wood, BlockDefinition<CrucibleBlock>> builder = ImmutableMap.builder();
        for (Wood value : Wood.values()) {
            builder.put(value, BLOCKS.burnableBlock(titleCase(value.getSerializedName()) + " Crucible", value.getSerializedName() + "_crucible", EXATFCCrucibleBlock::new));
        }
        return builder.build();
    });
    public static final Map<Wood, BlockDefinition<BarrelBlock>> TFC_BARRELS = Util.make(() -> {
        ImmutableMap.Builder<Wood, BlockDefinition<BarrelBlock>> builder = ImmutableMap.builder();
        for (Wood value : Wood.values()) {
            builder.put(value, BLOCKS.burnableBlock(titleCase(value.getSerializedName()) + " Barrel", value.getSerializedName() + "_barrel", EXATFCBarrelBlock::new));
        }
        return builder.build();
    });
    public static final Map<Wood, BlockDefinition<SieveBlock>> TFC_SIEVES = Util.make(() -> {
        ImmutableMap.Builder<Wood, BlockDefinition<SieveBlock>> builder = ImmutableMap.builder();
        for (Wood value : Wood.values()) {
            builder.put(value, BLOCKS.burnableBlock(titleCase(value.getSerializedName()) + " Sieve", value.getSerializedName() + "_sieve", EXATFCSieveBlock::new));
        }
        return builder.build();
    });

    public static List<BlockDefinition<?>> getDefinitions() {
        return BLOCKS.getRegistry();
    }
}
