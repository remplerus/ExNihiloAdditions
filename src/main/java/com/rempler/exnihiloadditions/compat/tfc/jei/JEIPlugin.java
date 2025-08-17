package com.rempler.exnihiloadditions.compat.tfc.jei;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXATFCBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.compat.jei.RecipeTypes;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

     @Nonnull
     @Override
     public ResourceLocation getPluginUid() {
         return ExNihiloAdditions.rl("tfc");
     }

     @Override
     public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
         if (!ExNihiloAdditions.isEMILoaded) {
             EXATFCBlocks.TFC_CRUCIBLES.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(blockDefinition.itemStack(), RecipeTypes.MELTING, RecipeTypes.HEAT);
             });

             EXATFCBlocks.TFC_BARRELS.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(
                         blockDefinition.itemStack(),
                         RecipeTypes.SOLIDIFYING,
                         RecipeTypes.TRANSITION,
                         RecipeTypes.PRECIPITATE,
                         RecipeTypes.COMPOST);
             });

             EXATFCBlocks.TFC_SIEVES.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(blockDefinition.itemStack(), RecipeTypes.DRY_SIFTING, RecipeTypes.WET_SIFTING);
             });
         }
    }

}