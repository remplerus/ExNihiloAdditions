package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import com.rempler.exnihiloadditions.compat.thermal.EXAThermalItems;
import net.minecraft.data.PackOutput;
import novamachina.novacore.data.AbstractLangGenerator;

public class EXALangProvider extends AbstractLangGenerator {

    public EXALangProvider(PackOutput output, String locale) {
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
        if (ExNihiloAdditions.isTFCLoaded) {
            EXATFCBlocks.getDefinitions().forEach(this::addBlockName);
        }
        if (ExNihiloAdditions.isBotaniaLoaded) {
            EXABotaniaItems.getDefinitions().forEach(this::addItemName);
        }
        if (ExNihiloAdditions.isThermalLoaded) {
            EXAThermalItems.getDefinitions().forEach(this::addItemName);
        }
        add("tooltip.doll.basalz", "Add to a barrel of lava to spawn a Basalz");
        add("tooltip.doll.blitz", "Add to a barrel of lava to spawn a Blitz");
        add("tooltip.doll.blizz", "Add to a barrel of water to spawn a Blizz");
    }

}
