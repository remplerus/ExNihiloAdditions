package com.rempler.exnihiloadditions.custom;

import com.rempler.exnihiloadditions.custom.config.CustomDollConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import com.rempler.exnihiloadditions.custom.items.CustomDollItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * GAME bus event handlers for custom features.
 *
 * Registered via NeoForge.EVENT_BUS.addListener() in ExNihiloAdditions constructor.
 */
public class EXNACommonEventHandlers {

    /**
     * Fires on the server thread before BarrelBlock.useItemOn() is called.
     * Sets PENDING_CONFIG so that CustomDollItem.getSpawnFluid() and spawnMob()
     * can retrieve the correct config without ItemStack access.
     */
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;

        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof CustomDollItem)) return;

        String dollId = stack.getOrDefault(EXNACustomComponents.VARIANT_ID.get(), "");
        if (dollId.isEmpty()) return;

        CustomDollConfig config = CustomVariantLoader.getDoll(dollId);
        // null if unknown id — getSpawnFluid() will return EMPTY and ENS skips the doll
        CustomDollItem.PENDING_CONFIG.set(config);
    }
}
