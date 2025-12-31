package com.rempler.exnihiloadditions.data.models;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.compat.thermal.EXAThermalItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.novacore.world.item.ItemDefinition;

public class EXAItemModelGenerator extends ItemModelProvider {
    public EXAItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExNihiloAdditions.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        if (ExNihiloAdditions.isBotaniaLoaded) {
            EXABotaniaItems.getDefinitions().stream().filter((def) -> def.getType() == ItemDefinition.ItemType.TOOL).forEach((definition) -> {
                this.singleTexture(definition.getId().getPath(), new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(this.modid, "item/botania/" + definition.getId().getPath()));
            });
        }
        if (ExNihiloAdditions.isThermalLoaded) {
            EXAThermalItems.getDefinitions().stream().filter((def) -> def.getType() == ItemDefinition.ItemType.OTHER).forEach((definition) -> {
                this.singleTexture(definition.getId().getPath(), new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(this.modid, "item/thermal/" + definition.getId().getPath()));
            });
        }
    }
}
