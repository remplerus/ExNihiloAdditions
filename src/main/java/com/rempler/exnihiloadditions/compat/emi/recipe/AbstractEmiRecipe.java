package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.EmiRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEmiRecipe implements EmiRecipe {
    protected final ResourceLocation id;

    AbstractEmiRecipe(Recipe<?> recipe) {
        this.id = EXNEMIPlugin.getPluginIdFromRecipe(recipe);
    }

    AbstractEmiRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }
}
