package com.rempler.exnihiloadditions.custom.items;

import com.rempler.exnihiloadditions.custom.EXNACustomComponents;
import com.rempler.exnihiloadditions.custom.config.CustomDollConfig;
import com.rempler.exnihiloadditions.custom.config.CustomVariantLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import novamachina.exnihilosequentia.world.item.DollItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class CustomDollItem extends DollItem {

    /**
     * Set by EXNACommonEventHandlers#onRightClickBlock before ENS barrel processes the doll.
     * ENS calls getSpawnFluid() (fluid check) and later spawnMob() (on tick) without ItemStack
     * access, so we pass the config through a ThreadLocal.
     */
    public static final ThreadLocal<CustomDollConfig> PENDING_CONFIG = new ThreadLocal<>();

    /**
     * Dummy super values — all real behaviour comes from the ThreadLocal config.
     * yOffset=1.0 is overridden in spawnMob() anyway.
     */
    public CustomDollItem(Properties properties) {
        super("minecraft", "pig", "minecraft", "water", 1.0, "item.exnihiloadditions.custom_doll", properties);
    }

    /**
     * Called by FluidsBarrelMode.doMobSpawn() to check whether the barrel fluid matches.
     * Reads the config set by the event handler right before this interaction.
     */
    @Override
    public Fluid getSpawnFluid() {
        CustomDollConfig config = PENDING_CONFIG.get();
        if (config == null) return Fluids.EMPTY;
        return BuiltInRegistries.FLUID.getOptional(config.fluid()).orElse(Fluids.EMPTY);
    }

    /**
     * Called by MobSpawnBarrelMode.tick() after the barrel enters MOB mode.
     * Spawns the entity from config and clears the ThreadLocal.
     */
    @Override
    public boolean spawnMob(@NotNull Level world, @NotNull BlockPos pos) {
        CustomDollConfig config = PENDING_CONFIG.get();
        PENDING_CONFIG.remove();
        if (config == null) return false;

        Optional<EntityType<?>> entityType = BuiltInRegistries.ENTITY_TYPE.getOptional(config.entity());
        if (entityType.isEmpty()) return false;

        Entity entity = entityType.get().create(world);
        if (entity == null) return false;

        entity.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        return world.addFreshEntity(entity);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        String dollId = stack.getOrDefault(EXNACustomComponents.VARIANT_ID.get(), "");
        if (!dollId.isEmpty()) {
            CustomDollConfig config = CustomVariantLoader.getDoll(dollId);
            if (config != null && config.displayName() != null) {
                return Component.literal(config.displayName());
            }
        }
        return super.getName(stack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext ctx,
                                @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        String dollId = stack.getOrDefault(EXNACustomComponents.VARIANT_ID.get(), "");
        if (dollId.isEmpty()) {
            tooltip.add(Component.translatable("item.exnihiloadditions.custom_doll.no_variant"));
            return;
        }
        CustomDollConfig config = CustomVariantLoader.getDoll(dollId);
        if (config != null) {
            tooltip.add(Component.literal("Fluid: " + config.fluid()));
            tooltip.add(Component.literal("Entity: " + config.entity()));
        }
    }
}
