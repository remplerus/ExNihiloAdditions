package com.rempler.exnihiloadditions.compat.tetra;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.world.level.block.InfestedLeavesBlock;

import java.util.List;

public class NihiloHelper {
    public static boolean canHammer(BlockState state) {
        return hammerable(state);
    }

    public static ObjectArrayList<ItemStack> getHammerDrops(Level level, BlockState state, RandomSource random) {
        return hammerDrops(level, state.getBlock(), random);
    }

    public static boolean canCrook(BlockState state) {
        return crookable(state);
    }

    public static ObjectArrayList<ItemStack> getCrookDrops(ObjectArrayList<ItemStack> generatedLoot, Level level, BlockState state, RandomSource random) {
        ObjectArrayList<ItemStack> newLoot = crookDrops(level, state.getBlock(), random);
        generatedLoot.addAll(newLoot);
        return generatedLoot;
    }

    private static boolean hammerable(BlockState state) {
        return ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(state.getBlock());
    }

    private static boolean crookable(BlockState state) {
        return ExNihiloRegistries.CROOK_REGISTRY.isCrookable(state.getBlock());
    }

    private static ObjectArrayList<ItemStack> hammerDrops(Level level, Block block, RandomSource random) {
        ObjectArrayList<ItemStack> loot = new ObjectArrayList<>();
        List<ItemStackWithChance> list = ExNihiloRegistries.HAMMER_REGISTRY.getResult(block);
        for (ItemStackWithChance stack : list) {
            if (random.nextFloat() <= stack.getChance() && stack.getStack() != ItemStack.EMPTY) {
                loot.add(stack.getStack());
            }
        }
        return loot;
    }

    private static ObjectArrayList<ItemStack> crookDrops(Level level, Block block, RandomSource random) {
        ObjectArrayList<ItemStack> loot = new ObjectArrayList<>();
        List<HarvestRecipe> list = ExNihiloRegistries.CROOK_REGISTRY.getDrops(block);
        for (HarvestRecipe recipe : list) {
            for (ItemStackWithChance stack : recipe.getDrops()) {
                if (random.nextFloat() <= stack.getChance() && stack.getStack() != ItemStack.EMPTY) {
                    loot.add(stack.getStack());
                }
            }
        }
        if (block instanceof InfestedLeavesBlock) {
            loot.add(new ItemStack(Items.STRING, random.nextInt(Config.getMaxBonusStringCount()) + Config.getMinStringCount()));
            if (random.nextDouble() <= 0.8) {
                loot.add(new ItemStack(EXNItems.SILKWORM));
            }
        }
        return loot;
    }

}
