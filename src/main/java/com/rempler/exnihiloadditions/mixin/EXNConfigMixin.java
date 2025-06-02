package com.rempler.exnihiloadditions.mixin;

import novamachina.exnihilosequentia.common.Config;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.nio.file.Path;

@Pseudo
@Mixin(Config.class)
public class EXNConfigMixin {

    @Inject(method = "loadConfig", at = @At(value = "INVOKE", target = "com/electronwill/nightconfig/core/file/CommentedFileConfig.load()V"), remap = false, cancellable = true)
    private static void exna$loadConfig(ModConfigSpec spec, @Nonnull Path path, CallbackInfo cir) {
        cir.cancel();
    }
}
