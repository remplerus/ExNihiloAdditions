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
        if (ExNihiloAdditions.isTFCLoaded) {
            EXNATFCBlocks.getDefinitions().forEach(this::addBlockName);
        }
    }

}
