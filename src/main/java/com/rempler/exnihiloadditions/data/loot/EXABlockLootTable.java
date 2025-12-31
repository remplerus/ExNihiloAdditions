package com.rempler.exnihiloadditions.data.loot;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import novamachina.novacore.data.loot.table.BlockLootTables;
import novamachina.novacore.world.level.block.BlockDefinition;
import org.checkerframework.checker.nullness.qual.NonNull;

public class EXABlockLootTable extends BlockLootTables {
    @Override
    protected void generate() {
        if (ExNihiloAdditions.isTFCLoaded) {
            add(this::createSingleTFCItemTable, EXATFCBlocks.getDefinitions().toArray(BlockDefinition[]::new));
        }
    }

    public LootTable.Builder createSingleTFCItemTable(@NonNull ItemLike item) {
        return createSingleModItemTable(item, "tfc");
    }


    private LootTable.Builder createSingleModItemTable(@NonNull ItemLike item, String modId) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(item, LootPool.lootPool().name(modId).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item)))
                .name(modId));
    }
}
