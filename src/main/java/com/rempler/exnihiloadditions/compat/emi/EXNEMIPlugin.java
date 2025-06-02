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
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.jei.sifting.JEISieveRecipe;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
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
import novamachina.novacore.util.IngredientUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@EmiEntrypoint
public class EXNEMIPlugin implements EmiPlugin {
    public static final List<Component> BARRELS = List.of(Component.translatable(ExNihiloTags.BARREL.location().toLanguageKey()));
    public static final ResourceLocation COMPOSTING_MODEL = ExNihiloAdditions.rl("item/barrel_composting");
    public static final ResourceLocation PRECIPITATING_SHEET = new ResourceLocation("exnihilosequentia", "textures/gui/jei_fluid_block_transform.png");

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
        for (EmiStack stack : getRawValues(ExNihiloTags.BARREL)) {
            emiRegistry.addWorkstation(COMPOSTING, stack);
            emiRegistry.addWorkstation(PRECIPITATING, stack);
            emiRegistry.addWorkstation(SOLIDIFYING, stack);
            emiRegistry.addWorkstation(TRANSITION, stack);
        }

        for (EmiStack stack : getRawValues(ExNihiloTags.CRUCIBLE)) {
            emiRegistry.addWorkstation(HEATING, stack);
            emiRegistry.addWorkstation(MELTING, stack);
        }
        emiRegistry.addWorkstation(FIRED_MELTING, EmiStack.of(EXNBlocks.FIRED_CRUCIBLE));
        emiRegistry.addWorkstation(FIRED_MELTING, EmiStack.of(EXNBlocks.CRIMSON_CRUCIBLE));
        emiRegistry.addWorkstation(FIRED_MELTING, EmiStack.of(EXNBlocks.WARPED_CRUCIBLE));

        for (EmiStack stack : getRawValues(ExNihiloTags.SIEVE)) {
            emiRegistry.addWorkstation(SIFTING, stack);
            emiRegistry.addWorkstation(WATER_SIFTING, stack);
        }

        for (EmiStack stack : getRawValues(ExNihiloTags.CROOK)) {
            emiRegistry.addWorkstation(HARVESTING, stack);
        }

        for (EmiStack stack : getRawValues(ExNihiloTags.HAMMER)) {
            emiRegistry.addWorkstation(CRUSHING, stack);
        }
    }

    private void registerRecipeManagers(EmiRegistry emiRegistry) {
        for (CompostRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.COMPOST)) {
            addRecipeSafe(emiRegistry, () -> new EmiCompostRecipe(recipe), recipe);
        }
        for (CrushingRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.CRUSHING)) {
            addRecipeSafe(emiRegistry, () -> new EmiCrushingRecipe(recipe), recipe);
        }
        for (HarvestRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.HARVEST)) {
            addRecipeSafe(emiRegistry, () -> new EmiHarvestingRecipe(recipe), recipe);
        }
        for (HeatRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.HEAT)) {
            addRecipeSafe(emiRegistry, () -> new EmiHeatRecipe(recipe), recipe);
        }
        for (MeltingRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.MELTING)) {
            addRecipeSafe(emiRegistry, () -> new EmiMeltingRecipe(recipe, recipe.getCrucibleType()), recipe);
        }
        for (PrecipitateRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.PRECIPITATE)) {
            addRecipeSafe(emiRegistry, () -> new EmiPrecipitateRecipe(recipe), recipe);
        }

        List<JEISieveRecipe> drySieveRecipes = getRecipeList(emiRegistry, false);
        List<JEISieveRecipe> wetSieveRecipes = getRecipeList(emiRegistry, true);
        int index = 0;
        for (JEISieveRecipe recipe : drySieveRecipes) {
            emiRegistry.addRecipe(new EmiSiftingRecipe(generateRecipeId(recipe.getInputs().get(0), recipe.getMesh(), false, recipe.getInputs().get(1).get(0), index++), recipe, false));
        }
        index = 0;
        for (JEISieveRecipe recipe : wetSieveRecipes) {
            emiRegistry.addRecipe(new EmiSiftingRecipe(generateRecipeId(recipe.getInputs().get(0), recipe.getMesh(), true, recipe.getInputs().get(1).get(0), index++), recipe, true));
        }

        for (SolidifyingRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.SOLIDIFYING)) {
            addRecipeSafe(emiRegistry, () -> new EmiSolidifyingRecipe(recipe), recipe);
        }
        for (TransitionRecipe recipe : getRecipes(emiRegistry, EXNRecipeTypes.TRANSITION)) {
            addRecipeSafe(emiRegistry, () -> new EmiTransitionRecipe(recipe), recipe);
        }
    }

    private static <C extends Container, T extends Recipe<C>> Iterable<T> getRecipes(EmiRegistry registry, RecipeType<T> type) {
        return registry.getRecipeManager().getAllRecipesFor(type).stream()::iterator;
    }

    private static void addRecipeSafe(EmiRegistry registry, Supplier<EmiRecipe> supplier, Recipe<?> recipe) {
        try {
            registry.addRecipe(supplier.get());
        } catch (Throwable e) {
            ExNihiloAdditions.LOGGER.warn("Exception thrown when parsing modded recipe {}", recipe.getId(), e);
        }
    }

    private static List<EmiStack> getRawValues(TagKey<Item> key) {
        List<EmiStack> list = new ArrayList<>();
        List<Holder<Item>> collection = BuiltInRegistries.ITEM.getTag(key).stream()
                .flatMap(HolderSet.ListBacked::stream)
                .toList();
        for (Holder<Item> item : collection) {
            EmiStack stack = EmiStack.of(item.get());
            if (stack != null) {
                list.add(stack);
            }
        }
        return list;
    }

    private ResourceLocation generateRecipeId(List<ItemStack> itemStacks, ItemStack mesh, boolean isWaterlogged, ItemStack itemStack, int index) {
        String baseId = "/emi_sifting/" + itemStacks.get(0).getItem() + "/" + mesh.getItem().toString().toLowerCase() + (isWaterlogged ? "_waterlogged" : "") + "_" + itemStack.getItem() + "_" + index;
        return new ResourceLocation(ExNihiloAdditions.MODID, baseId);
    }

    @Nonnull
    private List<JEISieveRecipe> getRecipeList(EmiRegistry emiRegistry, boolean isWaterLogged) {
        Iterable<SiftingRecipe> recipeList = getRecipes(emiRegistry, EXNRecipeTypes.SIFTING);
        Set<Ingredient> ingredients = new HashSet<>();
        recipeList.forEach((recipe) -> {
            Ingredient recipeIngredient = recipe.getInput();
            if (ingredients.stream().noneMatch((ingredient) -> IngredientUtils.areIngredientsEqual(ingredient, recipeIngredient))) {
                ingredients.add(recipeIngredient);
            }

        });
        return Arrays.stream(MeshType.values()).filter((enumMesh) -> enumMesh != MeshType.NONE).flatMap((enumMesh) -> {
            ItemStack mesh = new ItemStack(MeshItem.getMesh(enumMesh));
            return ingredients.stream().flatMap((ingredient) -> {
                List<SiftingRecipe> drops = ExNihiloRegistries.SIEVE_REGISTRY.getDrops(ingredient.getItems()[0].getItem(), enumMesh, isWaterLogged);
                if (drops.isEmpty()) {
                    return null;
                } else {
                    List<List<ItemStack>> input = new ArrayList<>(Arrays.asList(Collections.singletonList(mesh), Arrays.asList(ingredient.getItems())));
                    return List.of(drops).stream().map((results) -> new JEISieveRecipe(input, results));
                }
            });
        }).collect(Collectors.toList());
    }
}
