package com.rempler.exnihiloadditions.registers;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.List;

public class EXABlocks {
    public static final BlockRegistry BLOCKS = new BlockRegistry(ExNihiloAdditions.ModIds.MODID);

    public static List<BlockDefinition<?>> getDefinitions() {
        return BLOCKS.getRegistry();
    }
}
