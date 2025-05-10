package com.rempler.exnihiloadditions.data.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import novamachina.novacore.data.loot.LootProvider;

import java.util.List;

public class EXNCLootProvider extends LootProvider {
    public EXNCLootProvider(PackOutput output) {
        super(output, List.of(new SubProviderEntry(EXNCBlockLootTable::new, LootContextParamSets.BLOCK)));
    }
}
