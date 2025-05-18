package com.rempler.exnihiloadditions.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.block.InfestingLeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "novamachina/exnihilosequentia/world/item/SilkwormItem")
public class EXNSilkwormMixin {
    @Inject(method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;", at = @At("HEAD"))
    private void exna$useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        // Checks agains TFC leaves
        if (state.is(TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation("tfc", "fruit_tree_leaves")))) {
            cir.setReturnValue(doOnUse(context));
        } else if (state.is(BlockTags.LEAVES)){
            cir.setReturnValue(doOnUse(context));
        }
    }

    private InteractionResult doOnUse(UseOnContext context) {
        if (!context.getPlayer().isCreative()) {
            context.getItemInHand().shrink(1);
        }
        InfestingLeavesBlock.normalToInfesting(context.getLevel(), context.getClickedPos());
        return InteractionResult.SUCCESS;
    }
}
