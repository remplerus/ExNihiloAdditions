package com.rempler.exnihiloadditions.data.models;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.world.item.ItemDefinition;

public class EXABotaniaItemModelGenerator extends ItemModelProvider {
    public EXABotaniaItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExNihiloAdditions.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        EXNItems.getDefinitions().stream().filter((def) -> def.getType() == ItemDefinition.ItemType.TOOL).forEach((definition) -> {
            this.singleTexture(definition.getId().getPath(), new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(this.modid, "item/botania/" + definition.getId().getPath()));
        });
    }
}
