package com.rempler.exnihiloadditions.compat.emi.client;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ModelEvent;

public class EXAEMIClientSetup {
    public static void register(IEventBus event) {
        event.addListener(EXAEMIClientSetup::registerAdditionalModels);
    }

    private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.inventory(ExNihiloAdditions.rl("block/barrel_composting")));
        event.register(ModelResourceLocation.inventory(EXNEMIPlugin.COMPOSTING_MODEL));
    }
}
