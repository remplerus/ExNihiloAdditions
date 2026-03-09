package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import net.minecraft.data.PackOutput;
import novamachina.novacore.data.AbstractLangGenerator;

public class EXNALangProvider extends AbstractLangGenerator {

    public EXNALangProvider(PackOutput output, String locale) {
        super(output, ExNihiloAdditions.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ExNihiloAdditions.MODID, "Ex Nihilo: Additions");
        add("emi.category.exnihiloadditions.composting", "Composting");
        add("emi.category.exnihiloadditions.crushing", "Crushing");
        add("emi.category.exnihiloadditions.harvesting", "Harvesting");
        add("emi.category.exnihiloadditions.heating", "Heating");
        add("emi.category.exnihiloadditions.fired_melting", "Fired Melting");
        add("emi.category.exnihiloadditions.melting", "Melting");
        add("emi.category.exnihiloadditions.precipitating", "Precipitate");
        add("emi.category.exnihiloadditions.sifting", "Sifting");
        add("emi.category.exnihiloadditions.water_sifting", "Water Sifting");
        add("emi.category.exnihiloadditions.solidifying", "Solidifying");
        add("emi.category.exnihiloadditions.transition", "Transition");
        add("exnihilosequentia.barrels", "Barrels");
        add("block.exnihiloadditions.custom_sieve", "Custom Sieve");
        add("block.exnihiloadditions.custom_barrel", "Custom Barrel");
        add("block.exnihiloadditions.custom_crucible", "Custom Crucible");
        add("item.exnihiloadditions.custom_doll", "Custom Doll");
        add("item.exnihiloadditions.custom_doll.no_variant", "No doll variant configured");
        add("item.exnihiloadditions.custom_doll.not_enough_fluid", "Not enough fluid in barrel (needs 1000 mB)");
        add("item.exnihiloadditions.custom_doll.wrong_fluid", "Wrong fluid in barrel");
        add("item.exnihiloadditions.custom_doll.variant", "Add to a barrel of %s to spawn a %s");

        if (ExNihiloAdditions.isTFCLoaded) {
            EXNATFCBlocks.getDefinitions().forEach(this::addBlockName);
        }
    }
}
