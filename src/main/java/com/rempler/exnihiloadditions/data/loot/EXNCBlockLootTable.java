package com.rempler.exnihiloadditions.data.loot;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import novamachina.novacore.data.loot.table.BlockLootTables;
import novamachina.novacore.world.level.block.BlockDefinition;

public class EXNCBlockLootTable extends BlockLootTables {
    @Override
    protected void generate() {
        if (ExNihiloAdditions.isTFCLoaded) {
            add(this::createSingleItemTable, EXNATFCBlocks.getDefinitions().toArray(BlockDefinition[]::new));
        }
    }
}
