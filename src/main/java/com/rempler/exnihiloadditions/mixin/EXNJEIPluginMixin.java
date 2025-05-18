package com.rempler.exnihiloadditions.mixin;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "novamachina/exnihilosequentia/common/compat/jei/JEIPlugin", remap = false)
public class EXNJEIPluginMixin {
    @Inject(method = "registerCategories(Lmezz/jei/api/registration/IRecipeCategoryRegistration;)Z", at = @At("HEAD"), cancellable = true)
    private void exna$registerCategories(IRecipeCategoryRegistration context, CallbackInfo ci) {
        if (ExNihiloAdditions.isEMILoaded) {
            ci.cancel();
        }
    }
    @Inject(method = "registerRecipeCatalysts(Lmezz/jei/api/registration/IRecipeCatalystRegistration;)Z", at = @At("HEAD"), cancellable = true)
    private void exna$registerRecipeCatalysts(IRecipeCatalystRegistration context, CallbackInfo ci) {
        if (ExNihiloAdditions.isEMILoaded) {
            ci.cancel();
        }
    }
    @Inject(method = "registerRecipes(Lmezz/jei/api/registration/IRecipeRegistration;)Z", at = @At("HEAD"), cancellable = true)
    private void exna$registerRecipes(IRecipeRegistration context, CallbackInfo ci) {
        if (ExNihiloAdditions.isEMILoaded) {
            ci.cancel();
        }
    }
}
