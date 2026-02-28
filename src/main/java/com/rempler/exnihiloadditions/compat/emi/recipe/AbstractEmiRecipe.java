package com.rempler.exnihiloadditions.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEmiRecipe implements EmiRecipe {
    protected final ResourceLocation id;

    AbstractEmiRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }
}
