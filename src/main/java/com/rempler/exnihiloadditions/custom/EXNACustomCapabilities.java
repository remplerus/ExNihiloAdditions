package com.rempler.exnihiloadditions.custom;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import novamachina.exnihilosequentia.world.item.capability.BarrelInventoryHandler;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.level.material.capability.BarrelFluidHandler;
import novamachina.exnihilosequentia.world.level.material.capability.CrucibleFluidHandler;

/**
 * Registers NeoForge capabilities for our custom block entity types.
 *
 * ENS registers capabilities only for its own block entity types. Because our
 * custom types are separate registrations, we must mirror those registrations
 * here using the same ENS handler factories (BarrelFluidHandler, etc.).
 * The previous approach of checking `be instanceof IFluidHandler` was wrong
 * because neither barrel nor crucible BEs implement IFluidHandler themselves.
 *
 * Registered via eventBus.addListener() in ExNihiloAdditions constructor.
 */
public class EXNACustomCapabilities {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_BARREL.getType(),
                (be, side) -> BarrelFluidHandler.getHandler(be));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_BARREL.getType(),
                (be, side) -> BarrelInventoryHandler.getHandler(be));

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_CRUCIBLE.getType(),
                (be, side) -> CrucibleFluidHandler.getHandler(be));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_CRUCIBLE.getType(),
                (be, side) -> MeltableItemHandler.getHandler(be));

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_FIRED_CRUCIBLE.getType(),
                (be, side) -> CrucibleFluidHandler.getHandler(be));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                EXNACustomBlockEntities.CUSTOM_FIRED_CRUCIBLE.getType(),
                (be, side) -> MeltableItemHandler.getHandler(be));
    }
}
