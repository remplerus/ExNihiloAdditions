package com.rempler.exnihiloadditions.compat.tfc.client;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlockEntites;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(
        modid = ExNihiloAdditions.MODID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD)
public class EXNATFCClientSetup {

    public static void init(@Nonnull final FMLClientSetupEvent event) {
        BarrelRender.register(EXNATFCBlockEntites.WOODEN_BARREL_ENTITY.getType());
        CrucibleRender.register(EXNATFCBlockEntites.WOODEN_CRUCIBLE_ENTITY.getType());
        SieveRender.register(EXNATFCBlockEntites.WOODEN_SIEVE_ENTITY.getType());
    }
}
