package com.rempler.exnihiloadditions.compat.botania;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.List;

public class EXABotaniaBlocks {
    public static BlockRegistry BLOCKS = new BlockRegistry(ExNihiloAdditions.MODID);

    public static List<BlockDefinition<?>> getDefinitions() {
        return BLOCKS.getRegistry();
    }
}
