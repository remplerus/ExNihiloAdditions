package com.rempler.exnihiloadditions.compat.tfc.rei;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.neoforged.fml.ModList;

@REIPluginClient
public class TFCREIPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        if (!shouldRegister()) return;

        // TFC Crucibles -> Melting + Heat
        EXNATFCBlocks.TFC_CRUCIBLES.values().forEach(blockDefinition -> {
            registry.addWorkstations(EXAREIPlugin.MELTING, EntryStacks.of(blockDefinition.itemStack()));
            registry.addWorkstations(EXAREIPlugin.HEAT, EntryStacks.of(blockDefinition.itemStack()));
        });

        // TFC Barrels -> Solidifying, Transition, Precipitate, Compost
        EXNATFCBlocks.TFC_BARRELS.values().forEach(blockDefinition -> {
            registry.addWorkstations(EXAREIPlugin.SOLIDIFYING, EntryStacks.of(blockDefinition.itemStack()));
            registry.addWorkstations(EXAREIPlugin.TRANSITION, EntryStacks.of(blockDefinition.itemStack()));
            registry.addWorkstations(EXAREIPlugin.PRECIPITATE, EntryStacks.of(blockDefinition.itemStack()));
            registry.addWorkstations(EXAREIPlugin.COMPOST, EntryStacks.of(blockDefinition.itemStack()));
        });

        // TFC Sieves -> Dry Sifting, Wet Sifting
        EXNATFCBlocks.TFC_SIEVES.values().forEach(blockDefinition -> {
            registry.addWorkstations(EXAREIPlugin.DRY_SIFTING, EntryStacks.of(blockDefinition.itemStack()));
            registry.addWorkstations(EXAREIPlugin.WET_SIFTING, EntryStacks.of(blockDefinition.itemStack()));
        });
    }

    private boolean shouldRegister() {
        return ModList.get().isLoaded("roughlyenoughitems")
                && !ModList.get().isLoaded("emi")
                && ExNihiloAdditions.isTFCLoaded;
    }
}
