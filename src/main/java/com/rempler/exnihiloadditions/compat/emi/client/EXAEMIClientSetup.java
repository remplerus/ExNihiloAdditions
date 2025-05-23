package com.rempler.exnihiloadditions.compat.emi.client;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class EXAEMIClientSetup {
    public static void register(IEventBus event) {
        event.addListener(EXAEMIClientSetup::registerAdditionalModels);
    }

    private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(ExNihiloAdditions.rl("block/barrel_composting"));
        event.register(EXNEMIPlugin.COMPOSTING_MODEL);
    }
}
