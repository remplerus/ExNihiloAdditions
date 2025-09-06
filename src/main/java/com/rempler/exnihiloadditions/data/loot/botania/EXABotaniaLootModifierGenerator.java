package com.rempler.exnihiloadditions.data.loot.botania;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.botania.loot.SaplingModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class EXABotaniaLootModifierGenerator extends GlobalLootModifierProvider {
    public EXABotaniaLootModifierGenerator(PackOutput output) {
        super(output, ExNihiloAdditions.MODID);
    }

    @Override
    protected void start() {
        this.add("sapling_modifier", new SaplingModifier(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(EXABotaniaItems.dreamwoodCrook)).build()
        }));
    }
}
