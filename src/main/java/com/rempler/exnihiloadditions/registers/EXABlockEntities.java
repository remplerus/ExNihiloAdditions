package com.rempler.exnihiloadditions.registers;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractFiredCrucibleBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractSieveBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractStoneBarrelBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractWoodBarrelBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractWoodCrucibleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.FiredCrucibleBlockBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.StoneBarrelBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.WoodBarrelBlockEntity;
import novamachina.novacore.core.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

import javax.annotation.Nonnull;
import java.util.List;

public class EXABlockEntities {
    public static final BlockEntityTypeRegistry BLOCK_ENTITY_TYPES = new BlockEntityTypeRegistry(ExNihiloAdditions.ModIds.MODID);

    @Nonnull
    public static BlockEntityTypeDefinition<? extends AbstractWoodCrucibleBlockEntity> WOODEN_CRUCIBLE_ENTITY =
            BLOCK_ENTITY_TYPES.create("crucibles", (BlockEntityTypeRegistry.BlockEntityFactory<? extends AbstractWoodCrucibleBlockEntity>) DefaultItems.CRUCIBLES.createBlockEntityType(AbstractWoodCrucibleBlockEntity::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends FiredCrucibleBlockBlockEntity> FIRED_CRUCIBLE_ENTITY =
            BLOCK_ENTITY_TYPES.create("fired_crucibles", (BlockEntityTypeRegistry.BlockEntityFactory<? extends FiredCrucibleBlockBlockEntity>) DefaultItems.FIRED_CRUCIBLES.createBlockEntityType(AbstractFiredCrucibleBlockEntity::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends WoodBarrelBlockEntity> WOODEN_BARREL_ENTITY =
            BLOCK_ENTITY_TYPES.create("wooden_barrels", (BlockEntityTypeRegistry.BlockEntityFactory<? extends WoodBarrelBlockEntity>) DefaultItems.BARRELS.createBlockEntityType(AbstractWoodBarrelBlockEntity::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends StoneBarrelBlockEntity> STONE_BARREL_ENTITY =
            BLOCK_ENTITY_TYPES.create("stone_barrels", (BlockEntityTypeRegistry.BlockEntityFactory<? extends StoneBarrelBlockEntity>) DefaultItems.STONE_BARRELS.createBlockEntityType(AbstractStoneBarrelBlockEntity::new));
    @Nonnull
    public static BlockEntityTypeDefinition<? extends SieveBlockEntity> SIEVE_ENTITY =
            BLOCK_ENTITY_TYPES.create("sieves", (BlockEntityTypeRegistry.BlockEntityFactory<? extends SieveBlockEntity>) DefaultItems.SIEVES.createBlockEntityType(AbstractSieveBlockEntity::new));

    public static List<BlockEntityTypeDefinition<? extends BlockEntity>> getDefinitions() {
        return BLOCK_ENTITY_TYPES.getRegistry();
    }
}
