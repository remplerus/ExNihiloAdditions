package com.rempler.exnihiloadditions.compat.botania.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import vazkii.botania.api.item.SequentialBreaker;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.RingOfThorItem;

public class TerrasteelCrook extends CrookItem implements SequentialBreaker {
    public TerrasteelCrook(Tier tier, int maxDamage) {
        super(tier, maxDamage);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        BlockHitResult raycast = ToolCommons.raytraceFromEntity(player, 10, false);
        if (!player.level().isClientSide && !player.isCrouching() && raycast.getType() == HitResult.Type.BLOCK) {
            Direction face = raycast.getDirection();
            this.breakOtherBlock(player, itemstack, pos, pos, face);
        }

        return false;
    }

    @Override
    public void breakOtherBlock(Player player, ItemStack stack, BlockPos pos, BlockPos originPos, Direction side) {
        Level level = player.level();
        BlockState state = level.getBlockState(pos);

        if (!this.isCorrectToolForDrops(stack, state)) {
            return;
        }

        boolean thor = !RingOfThorItem.getThorRing(player).isEmpty();
        boolean doX = thor || side.getStepX() == 0;
        boolean doY = thor || side.getStepY() == 0;
        boolean doZ = thor || side.getStepZ() == 0;
        int range = 1;

        Vec3i beginDiff = new Vec3i(doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0);
        Vec3i endDiff = new Vec3i(doX ? range : 0, doY ? 1 : 0, doZ ? range : 0);

        ToolCommons.removeBlocksInIteration(player, stack, level, originPos, beginDiff, endDiff, state1 -> this.isCorrectToolForDrops(stack, state1));
    }
}
