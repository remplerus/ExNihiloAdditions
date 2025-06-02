package com.rempler.exnihiloadditions.compat.tfc.client;

import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;

import javax.annotation.Nonnull;

public class EXNATFCClientSetup {

    public static void init(@Nonnull final FMLClientSetupEvent event) {
        BarrelRender.register(EXNATFCBlockEntites.WOODEN_BARREL_ENTITY.getType());
        CrucibleRender.register(EXNATFCBlockEntites.WOODEN_CRUCIBLE_ENTITY.getType());
        SieveRender.register(EXNATFCBlockEntites.WOODEN_SIEVE_ENTITY.getType());
    }
}
