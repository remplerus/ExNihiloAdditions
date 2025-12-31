package com.rempler.exnihiloadditions.compat.tetra;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolAction;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import se.mickelus.tetra.util.ToolActionHelper;

public class EXATetraToolActions {
    public static final ToolAction crook = ToolAction.get("crook_dig");
    public static final TagKey<Block> crookMineable = BlockTags.create(new ResourceLocation(ExNihiloSequentia.MOD_ID,"mineable/crook"));

    public static void init() {
        ToolActionHelper.appropriateTools.put(crook, crookMineable);
    }
}
