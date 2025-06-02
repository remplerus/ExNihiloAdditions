package com.rempler.exnihiloadditions.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import novamachina.novacore.data.loot.LootProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EXNCLootProvider extends LootProvider {
    public EXNCLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, List.of(new SubProviderEntry(EXNCBlockLootTable::new, LootContextParamSets.BLOCK)), provider);
    }
}
