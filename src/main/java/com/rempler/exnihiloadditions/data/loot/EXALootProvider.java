package com.rempler.exnihiloadditions.data.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import novamachina.novacore.data.loot.LootProvider;

import java.util.List;

public class EXALootProvider extends LootProvider {
    public EXALootProvider(PackOutput output) {
        super(output, List.of(new SubProviderEntry(EXABlockLootTable::new, LootContextParamSets.BLOCK)));
    }
}
