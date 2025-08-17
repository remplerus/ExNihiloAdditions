package com.rempler.exnihiloadditions.compat.tetra.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rempler.exnihiloadditions.compat.tetra.EXATetraToolActions;
import com.rempler.exnihiloadditions.compat.tetra.NihiloHelper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import se.mickelus.tetra.items.modular.ItemModularHandheld;

import java.util.function.Supplier;

public class EXACrookLootModifier extends LootModifier {
    public static final Supplier<Codec<EXACrookLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(instance -> codecStart(instance)
                    .apply(instance, EXACrookLootModifier::new)));

    public EXACrookLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        ItemStack tool = lootContext.getParamOrNull(LootContextParams.TOOL);
        Entity entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
        BlockState blockState = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (tool != null && blockState != null && entity instanceof Player && tool.getItem() instanceof ItemModularHandheld &&
                tool.canPerformAction(EXATetraToolActions.crook) && NihiloHelper.canCrook(blockState)) {
            ObjectArrayList<ItemStack> changedLoot = NihiloHelper.getCrookDrops(objectArrayList, entity.level(), blockState, lootContext.getRandom());
            if (!changedLoot.isEmpty()) {
                return changedLoot;
            }
        }
        return objectArrayList;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
