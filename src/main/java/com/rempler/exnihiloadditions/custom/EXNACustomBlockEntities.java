package com.rempler.exnihiloadditions.custom;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.custom.blocks.CustomBarrelBlockEntity;
import com.rempler.exnihiloadditions.custom.blocks.CustomCrucibleBlockEntity;
import com.rempler.exnihiloadditions.custom.blocks.CustomFiredCrucibleBlockEntity;
import com.rempler.exnihiloadditions.custom.blocks.CustomSieveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.novacore.NovaCore;
import novamachina.novacore.core.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.entity.BlockEntityTypeDefinition;

import java.util.List;

public class EXNACustomBlockEntities {

    public static final BlockEntityTypeRegistry BLOCK_ENTITY_TYPES =
            new BlockEntityTypeRegistry(ExNihiloAdditions.MODID, NovaCore.SERVICE_PROVIDER);

    public static final BlockEntityTypeDefinition<CustomSieveBlockEntity> CUSTOM_SIEVE =
            BLOCK_ENTITY_TYPES.create("custom_sieve", CustomSieveBlockEntity::new, EXNACustomBlocks.CUSTOM_SIEVE);

    public static final BlockEntityTypeDefinition<CustomBarrelBlockEntity> CUSTOM_BARREL =
            BLOCK_ENTITY_TYPES.create("custom_barrel", CustomBarrelBlockEntity::new, EXNACustomBlocks.CUSTOM_BARREL);

    public static final BlockEntityTypeDefinition<CustomCrucibleBlockEntity> CUSTOM_CRUCIBLE =
            BLOCK_ENTITY_TYPES.create("custom_crucible", CustomCrucibleBlockEntity::new, EXNACustomBlocks.CUSTOM_CRUCIBLE);

    public static final BlockEntityTypeDefinition<CustomFiredCrucibleBlockEntity> CUSTOM_FIRED_CRUCIBLE =
            BLOCK_ENTITY_TYPES.create("custom_fired_crucible", CustomFiredCrucibleBlockEntity::new, EXNACustomBlocks.CUSTOM_FIRED_CRUCIBLE);

    public static List<BlockEntityTypeDefinition<? extends BlockEntity>> getDefinitions() {
        return BLOCK_ENTITY_TYPES.getRegistry();
    }
}
