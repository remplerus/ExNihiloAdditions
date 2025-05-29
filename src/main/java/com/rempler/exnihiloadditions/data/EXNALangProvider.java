package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.api.DefaultItems;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.novacore.data.AbstractLangGenerator;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.Arrays;

public class EXNALangProvider extends AbstractLangGenerator {

    public EXNALangProvider(PackOutput output, String locale) {
        super(output, ExNihiloAdditions.ModIds.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ExNihiloAdditions.ModIds.MODID, "Ex Nihilo: Additions");
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
            EXNATFCBlocks.getDefinitions().forEach(this::addBlockName);
        }
        DefaultItems.STONE_BARRELS.forEach(this::addBlockName);
        DefaultItems.BARRELS.forEach(this::addBlockName);
        DefaultItems.FIRED_CRUCIBLES.forEach(this::addBlockName);
        DefaultItems.CRUCIBLES.forEach(this::addBlockName);
        DefaultItems.SIEVES.forEach(this::addBlockName);
    }


    protected void addBlockName(ItemLike definition) {
        this.add(getNameFromItemLike(definition), convertItemToName(definition));
    }

    private String convertItemToName(ItemLike itemLike) {
        String name = getNameFromItemLike(itemLike);
        if (name.contains("ars_nouveau")) {
            name = name.replace("ars_nouveau_", "ars_");
        }
        name = name.split("_", 2)[1];
        return properNaming(name);
    }

    private String getNameFromItemLike(ItemLike itemLike) {
        String name = itemLike.asItem().getDescription().toString().replace("translation{key='", "")
                .replace("', args=[]}", "");
        return name;
    }
}
