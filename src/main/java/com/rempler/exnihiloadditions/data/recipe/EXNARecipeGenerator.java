package com.rempler.exnihiloadditions.data.recipe;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.data.recipe.tfc.EXNATFCRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.novacore.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class EXNARecipeGenerator extends RecipeProvider {
    public EXNARecipeGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper, ExNihiloAdditions.MODID);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        if (ExNihiloAdditions.isTFCLoaded) {
            EXNATFCRecipes.init(consumer);
        }
    }
}
