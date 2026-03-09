package com.rempler.exnihiloadditions.client;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ModelEvent;

public class EXAClientSetup {
    public static void register(IEventBus event) {
        event.addListener(EXAClientSetup::registerAdditionalModels);
    }

    private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(ExNihiloAdditions.rl("block/barrel_composting")));
        event.register(ModelResourceLocation.standalone(ExNihiloAdditions.rl("item/barrel_composting")));
    }
}
