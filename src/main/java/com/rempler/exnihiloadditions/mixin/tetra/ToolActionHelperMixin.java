package com.rempler.exnihiloadditions.mixin.tetra;

import com.rempler.exnihiloadditions.compat.tetra.EXATetraToolActions;
import com.rempler.exnihiloadditions.compat.tetra.NihiloHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.mickelus.tetra.TetraToolActions;

@Mixin(targets = "se/mickelus/tetra/util/ToolActionHelper")
public abstract class ToolActionHelperMixin {
    @Inject(method = "isEffectiveOn(Lnet/minecraftforge/common/ToolAction;Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("RETURN"), cancellable = true, remap = false)
    private static void exa$isEffectiveOn(ToolAction action, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            if (action == TetraToolActions.hammer && NihiloHelper.canHammer(state)) {
                cir.setReturnValue(true);
            }
            if (action == EXATetraToolActions.crook && NihiloHelper.canCrook(state)) {
                cir.setReturnValue(true);
            }
        }
    }
}
