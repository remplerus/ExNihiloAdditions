package com.rempler.exnihiloadditions.compat.botania.item;

import com.rempler.exnihiloadditions.compat.botania.EXABotaniaConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import novamachina.exnihilosequentia.world.item.HammerItem;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.entity.PixieEntity;

public class ElementiumHammer extends HammerItem {
    public ElementiumHammer(@NotNull Tier tier, int maxDamage) {
        super(tier, maxDamage);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide) {

            if (Math.random() < EXABotaniaConfig.getPixieSpawnChance()) {
                Monster nearest = level.getNearestEntity(Monster.class, TargetingConditions.DEFAULT, entity, 5, 5, 5, this.getBoundingBox(pos, EXABotaniaConfig.getDetectSpawnRange()));
                PixieEntity pixie = new PixieEntity(level);
                pixie.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                pixie.setProps(nearest, entity, 0, (float) (5 * Math.random()));
                pixie.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
                level.addFreshEntity(pixie);
            }
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }

    @SuppressWarnings("SameParameterValue")
    private AABB getBoundingBox(BlockPos pos, double range) {
        int radius = (int) range / 2;
        return new AABB(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius));
    }
}
