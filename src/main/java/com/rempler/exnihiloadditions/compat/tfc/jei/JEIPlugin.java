package com.rempler.exnihiloadditions.compat.tfc.jei;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.jei.RecipeTypes;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

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
         if (!ExNihiloAdditions.isEMILoaded && ModList.get().isLoaded("exnjei")) {
             EXNATFCBlocks.TFC_CRUCIBLES.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(blockDefinition.itemStack(), RecipeTypes.MELTING, RecipeTypes.HEAT);
             });

             EXNATFCBlocks.TFC_BARRELS.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(
                         blockDefinition.itemStack(),
                         RecipeTypes.SOLIDIFYING,
                         RecipeTypes.TRANSITION,
                         RecipeTypes.PRECIPITATE,
                         RecipeTypes.COMPOST);
             });

             EXNATFCBlocks.TFC_SIEVES.values().stream().forEach(blockDefinition -> {
                 registration.addRecipeCatalyst(blockDefinition.itemStack(), RecipeTypes.WET_SIFTING, RecipeTypes.DRY_SIFTING);
             });
         }
    }

}