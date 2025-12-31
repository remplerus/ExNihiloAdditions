package com.rempler.exnihiloadditions.compat.tetra;

import net.minecraftforge.eventbus.api.IEventBus;

public class EXATetra {
    public EXATetra(IEventBus modEventBus) {
        EXATetraToolActions.init();
        EXATetraLootModifiers.LOOT_MODIFIERS.register(modEventBus);
    }
}
