package com.rempler.exnihiloadditions.compat.emi;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiCompostRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiCrushingRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiHarvestingRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiHeatRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiMeltingRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiPrecipitateRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiSiftingRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiSolidifyingRecipe;
import com.rempler.exnihiloadditions.compat.emi.recipe.EmiTransitionRecipe;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import java.util.List;
import java.util.function.Supplier;

@EmiEntrypoint
public class EXNEMIPlugin implements EmiPlugin {
    public static final List<Component> BARRELS = List.of(Component.translatable(ExNihiloTags.BARREL.location().toLanguageKey()));
    public static final ResourceLocation PRECIPITATING_SHEET = ResourceLocation.fromNamespaceAndPath("exnihilosequentia", "textures/gui/jei_fluid_block_transform.png");

    public static final EmiIngredient CRUCIBLE = EmiStack.of(EXNBlocks.ACACIA_CRUCIBLE);
    public static final EmiIngredient BARREL = EmiStack.of(EXNBlocks.ACACIA_BARREL);
    public static final EmiIngredient SIEVE = EmiStack.of(EXNBlocks.ACACIA_SIEVE);
    public static final EmiIngredient CROOK = EmiStack.of(EXNItems.CROOK_WOOD);
    public static final EmiIngredient HAMMER = EmiStack.of(EXNItems.HAMMER_WOOD);

    public static final EmiRecipeCategory COMPOSTING = new EmiRecipeCategory(ExNihiloAdditions.rl("composting"), BARREL);
    public static final EmiRecipeCategory CRUSHING = new EmiRecipeCategory(ExNihiloAdditions.rl("crushing"), HAMMER);
    public static final EmiRecipeCategory HARVESTING = new EmiRecipeCategory(ExNihiloAdditions.rl("harvesting"), CROOK);
    public static final EmiRecipeCategory HEATING = new EmiRecipeCategory(ExNihiloAdditions.rl("heating"), CRUCIBLE);
    public static final EmiRecipeCategory MELTING = new EmiRecipeCategory(ExNihiloAdditions.rl("melting"), CRUCIBLE);
    public static final EmiRecipeCategory FIRED_MELTING = new EmiRecipeCategory(ExNihiloAdditions.rl("fired_melting"), CRUCIBLE);
    public static final EmiRecipeCategory PRECIPITATING = new EmiRecipeCategory(ExNihiloAdditions.rl("precipitating"), BARREL);
    public static final EmiRecipeCategory SIFTING = new EmiRecipeCategory(ExNihiloAdditions.rl("sifting"), SIEVE);
    public static final EmiRecipeCategory WATER_SIFTING = new EmiRecipeCategory(ExNihiloAdditions.rl("water_sifting"), SIEVE);
    public static final EmiRecipeCategory SOLIDIFYING = new EmiRecipeCategory(ExNihiloAdditions.rl("solidifying"), BARREL);
    public static final EmiRecipeCategory TRANSITION = new EmiRecipeCategory(ExNihiloAdditions.rl("transition"), BARREL);

    @Override
    public void register(EmiRegistry emiRegistry) {
        registerCategories(emiRegistry);
        registerWorkstations(emiRegistry);
        registerRecipeManagers(emiRegistry);
    }

    private void registerCategories(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(COMPOSTING);
        emiRegistry.addCategory(CRUSHING);
        emiRegistry.addCategory(HARVESTING);
        emiRegistry.addCategory(HEATING);
        emiRegistry.addCategory(MELTING);
        emiRegistry.addCategory(FIRED_MELTING);
        emiRegistry.addCategory(PRECIPITATING);
        emiRegistry.addCategory(SIFTING);
        emiRegistry.addCategory(WATER_SIFTING);
        emiRegistry.addCategory(SOLIDIFYING);
        emiRegistry.addCategory(TRANSITION);
    }

    private void registerWorkstations(EmiRegistry emiRegistry) {
        emiRegistry.addWorkstation(COMPOSTING, EmiIngredient.of(ExNihiloTags.BARREL));
        emiRegistry.addWorkstation(PRECIPITATING, EmiIngredient.of(ExNihiloTags.BARREL));
        emiRegistry.addWorkstation(SOLIDIFYING, EmiIngredient.of(ExNihiloTags.BARREL));
        emiRegistry.addWorkstation(TRANSITION, EmiIngredient.of(ExNihiloTags.BARREL));

        emiRegistry.addWorkstation(HEATING, EmiIngredient.of(ExNihiloTags.CRUCIBLE));
        emiRegistry.addWorkstation(MELTING, EmiIngredient.of(ExNihiloTags.CRUCIBLE));

        emiRegistry.addWorkstation(FIRED_MELTING, EmiIngredient.of(ExNihiloAdditions.FIRED_CRUCIBLE));

        emiRegistry.addWorkstation(SIFTING, EmiIngredient.of(ExNihiloTags.SIEVE));
        emiRegistry.addWorkstation(WATER_SIFTING, EmiIngredient.of(ExNihiloTags.SIEVE));

        emiRegistry.addWorkstation(HARVESTING, EmiIngredient.of(ExNihiloTags.CROOK));

        emiRegistry.addWorkstation(CRUSHING, EmiIngredient.of(ExNihiloTags.HAMMER));
    }

    private void registerRecipeManagers(EmiRegistry emiRegistry) {
        for (RecipeHolder<CompostRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.COMPOST)) {
            addRecipeSafe(emiRegistry, () -> new EmiCompostRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<CrushingRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.CRUSHING)) {
            addRecipeSafe(emiRegistry, () -> new EmiCrushingRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<HarvestRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.HARVEST)) {
            addRecipeSafe(emiRegistry, () -> new EmiHarvestingRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<HeatRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.HEAT)) {
            addRecipeSafe(emiRegistry, () -> new EmiHeatRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<MeltingRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.MELTING)) {
            addRecipeSafe(emiRegistry, () -> new EmiMeltingRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<PrecipitateRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.PRECIPITATE)) {
            addRecipeSafe(emiRegistry, () -> new EmiPrecipitateRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<SiftingRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.SIFTING)) {
            addRecipeSafe(emiRegistry, () -> new EmiSiftingRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<SolidifyingRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.SOLIDIFYING)) {
            addRecipeSafe(emiRegistry, () -> new EmiSolidifyingRecipe(recipe), recipe.value());
        }
        for (RecipeHolder<TransitionRecipe> recipe : getRecipes(emiRegistry, EXNRecipeTypes.TRANSITION)) {
            addRecipeSafe(emiRegistry, () -> new EmiTransitionRecipe(recipe), recipe.value());
        }
    }

    private static <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getRecipes(EmiRegistry registry, RecipeType<T> type) {
        return registry.getRecipeManager().getAllRecipesFor(type);
    }

    private static void addRecipeSafe(EmiRegistry registry, Supplier<EmiRecipe> supplier, Recipe<?> recipe) {
        try {
            registry.addRecipe(supplier.get());
        } catch (Throwable e) {
            ExNihiloAdditions.LOGGER.warn("Exception thrown when parsing modded recipe {}", recipe, e);
        }
    }
}
