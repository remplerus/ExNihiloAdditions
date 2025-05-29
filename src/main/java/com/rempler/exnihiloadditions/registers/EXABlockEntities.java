package com.rempler.exnihiloadditions.registers;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractFiredCrucibleBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractSieveBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractStoneBarrelBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractWoodBarrelBlockEntity;
import com.rempler.exnihiloadditions.api.blocks.entities.AbstractWoodCrucibleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EXABlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ExNihiloAdditions.ModIds.MODID);

    public static final RegistryObject<BlockEntityType<AbstractFiredCrucibleBlockEntity>> FIRED_CRUCIBLE_ENTITY = BLOCK_ENTITIES.register("fired_crucible", () -> DefaultItems.FIRED_CRUCIBLES.createBlockEntityType(AbstractFiredCrucibleBlockEntity::new));
    public static final RegistryObject<BlockEntityType<AbstractWoodCrucibleBlockEntity>> WOODEN_CRUCIBLE_ENTITY = BLOCK_ENTITIES.register("crucible", () -> DefaultItems.CRUCIBLES.createBlockEntityType(AbstractWoodCrucibleBlockEntity::new));
    public static final RegistryObject<BlockEntityType<AbstractWoodBarrelBlockEntity>> WOODEN_BARREL_ENTITY = BLOCK_ENTITIES.register("barrel", () -> DefaultItems.BARRELS.createBlockEntityType(AbstractWoodBarrelBlockEntity::new));
    public static final RegistryObject<BlockEntityType<AbstractStoneBarrelBlockEntity>> STONE_BARREL_ENTITY = BLOCK_ENTITIES.register("stone_barrel", () -> DefaultItems.STONE_BARRELS.createBlockEntityType(AbstractStoneBarrelBlockEntity::new));
    public static final RegistryObject<BlockEntityType<AbstractSieveBlockEntity>> SIEVE_ENTITY = BLOCK_ENTITIES.register("sieve", () -> DefaultItems.SIEVES.createBlockEntityType(AbstractSieveBlockEntity::new));
}
