package com.rempler.exnihiloadditions.custom.items;

import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import com.rempler.exnihiloadditions.custom.blocks.CustomBarrelBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomCrucibleBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomFiredCrucibleBlock;
import com.rempler.exnihiloadditions.custom.blocks.CustomSieveBlock;
import com.rempler.exnihiloadditions.custom.config.CustomVariantConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CustomVariantBlockItem extends BlockItem {

    public CustomVariantBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        String variantId = stack.get(EXNACustomComponents.VARIANT_ID.get());
        if (variantId == null || variantId.isEmpty()) return super.getName(stack);
        CustomVariantConfig cfg = CustomVariantLoader.getVariant(variantId);
        if (cfg == null) return super.getName(stack);

        String suffix = switch (getBlock()) {
            case CustomBarrelBlock ignored       -> "Barrel";
            case CustomSieveBlock ignored        -> "Sieve";
            case CustomCrucibleBlock ignored     -> "Crucible";
            case CustomFiredCrucibleBlock ignored -> "Crucible";
            default                              -> null;
        };
        if (suffix == null) return super.getName(stack);
        return Component.literal(cfg.displayName() + " " + suffix);
    }
}
