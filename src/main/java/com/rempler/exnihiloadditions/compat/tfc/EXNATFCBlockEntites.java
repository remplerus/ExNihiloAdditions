package com.rempler.exnihiloadditions.compat.tfc;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.WoodCrucibleBlockEntity;
import novamachina.novacore.core.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

import javax.annotation.Nonnull;

import java.util.List;

public class EXNATFCBlockEntites {
    public static final BlockEntityTypeRegistry BLOCK_ENTITY_TYPES = new BlockEntityTypeRegistry(ExNihiloAdditions.MODID);

    private EXNATFCBlockEntites() {
    }

    @Nonnull
    public static BlockEntityTypeDefinition<? extends WoodCrucibleBlockEntity> WOODEN_CRUCIBLE_ENTITY = BLOCK_ENTITY_TYPES.create(
                    "crucibles",
                    WoodCrucibleBlockEntity::new,
                    EXNATFCBlocks.TFC_CRUCIBLES.values().toArray(BlockDefinition[]::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends BarrelBlockEntity> WOODEN_BARREL_ENTITY =
            BLOCK_ENTITY_TYPES.create(
                    "barrels",
                    WoodBarrelBlockEntity::new,
                    EXNATFCBlocks.TFC_BARRELS.values().toArray(BlockDefinition[]::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends SieveBlockEntity> WOODEN_SIEVE_ENTITY =
            BLOCK_ENTITY_TYPES.create(
                    "sieves",
                    SieveBlockEntity::new,
                    EXNATFCBlocks.TFC_SIEVES.values().toArray(BlockDefinition[]::new));


    public static List<BlockEntityTypeDefinition<? extends BlockEntity>> getDefinitions() {
        return BLOCK_ENTITY_TYPES.getRegistry();
    }
}
