package com.rempler.exnihiloadditions.compat.emi;

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
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
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

//@EmiEntrypoint
public class EXNEMIPlugin implements EmiPlugin {
    public static final ResourceLocation JEI_MID_SHEET = new ResourceLocation("exnihilosequentia", "textures/gui/jei_mid.png");
    public static final ResourceLocation HEATING_SHEET = new ResourceLocation("emi", "textures/gui/background.png");
    public static final ResourceLocation PRECIPITATING_SHEET = new ResourceLocation("exnihilosequentia", "textures/gui/jei_fluid_block_transform.png");
    public static final ResourceLocation SOLIDIFYING_SHEET = new ResourceLocation("exnihilosequentia", "textures/gui/jei_fluid_on_top.png");
    public static final ResourceLocation TRANSITION_SHEET = new ResourceLocation("exnihilosequentia", "textures/gui/jei_fluid_transform.png");

    public static final EmiIngredient CRUCIBLE = EmiIngredient.of(ExNihiloTags.CRUCIBLE);
    public static final EmiIngredient BARREL = EmiIngredient.of(ExNihiloTags.BARREL);
    public static final EmiIngredient SIEVE = EmiIngredient.of(ExNihiloTags.SIEVE);
    public static final EmiIngredient CROOK = EmiIngredient.of(ExNihiloTags.CROOK);
    public static final EmiIngredient HAMMER = EmiIngredient.of(ExNihiloTags.HAMMER);

    public static final EmiTexture COMPOSTING_TEXTURE = new EmiTexture(JEI_MID_SHEET, 0, 168, 166, 63);
    public static final EmiTexture CRUSHING_TEXTURE = new EmiTexture(JEI_MID_SHEET, 0, 56, 166, 58);
    public static final EmiTexture HARVESTING_TEXTURE = new EmiTexture(JEI_MID_SHEET, 0, 112, 166, 58);
    public static final EmiTexture HEATING_TEXTURE = new EmiTexture(HEATING_SHEET, 0, 134, 18, 34);
    public static final EmiTexture MELTING_TEXTURE = new EmiTexture(JEI_MID_SHEET, 0, 168, 166, 63);
    public static final EmiTexture PRECIPITATING_TEXTURE = new EmiTexture(PRECIPITATING_SHEET, 0, 0, 166, 63);
    public static final EmiTexture SIFTING_TEXTURE = new EmiTexture(JEI_MID_SHEET, 0, 0, 166, 58);
    public static final EmiTexture SOLIDIFYING_TEXTURE = new EmiTexture(SOLIDIFYING_SHEET, 0, 0, 166, 63);
    public static final EmiTexture TRANSITION_TEXTURE = new EmiTexture(TRANSITION_SHEET, 0, 0, 166, 63);

    public static final EmiRecipeCategory COMPOSTING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:composting"), BARREL, COMPOSTING_TEXTURE);
    public static final EmiRecipeCategory CRUSHING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:crushing"), HAMMER, CRUSHING_TEXTURE);
    public static final EmiRecipeCategory HARVESTING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:harvesting"), CROOK, HARVESTING_TEXTURE);
    public static final EmiRecipeCategory HEATING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:heating"), CRUCIBLE, HEATING_TEXTURE);
    public static final EmiRecipeCategory MELTING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:melting"), CRUCIBLE, MELTING_TEXTURE);
    public static final EmiRecipeCategory PRECIPITATING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:precipitating"), BARREL, PRECIPITATING_TEXTURE);
    public static final EmiRecipeCategory SIFTING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:sifting"), SIEVE, SIFTING_TEXTURE);
    public static final EmiRecipeCategory SOLIDIFYING = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:solidifying"), BARREL, SOLIDIFYING_TEXTURE);
    public static final EmiRecipeCategory TRANSITION = new EmiRecipeCategory(new ResourceLocation("exnihiloadditions:transition"), BARREL, TRANSITION_TEXTURE);

    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(COMPOSTING);
        emiRegistry.addWorkstation(COMPOSTING, BARREL);
        emiRegistry.addCategory(CRUSHING);
        emiRegistry.addWorkstation(CRUSHING, HAMMER);
        emiRegistry.addCategory(HARVESTING);
        emiRegistry.addWorkstation(HARVESTING, CROOK);
        emiRegistry.addCategory(HEATING);
        emiRegistry.addWorkstation(HEATING, CRUCIBLE);
        emiRegistry.addCategory(MELTING);
        emiRegistry.addWorkstation(MELTING, CRUCIBLE);
        emiRegistry.addCategory(PRECIPITATING);
        emiRegistry.addWorkstation(PRECIPITATING, BARREL);
        emiRegistry.addCategory(SIFTING);
        emiRegistry.addWorkstation(SIFTING, SIEVE);
        emiRegistry.addCategory(SOLIDIFYING);
        emiRegistry.addWorkstation(SOLIDIFYING, BARREL);
        emiRegistry.addCategory(TRANSITION);
        emiRegistry.addWorkstation(TRANSITION, BARREL);

        RecipeManager manager = emiRegistry.getRecipeManager();

        for (CompostRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.COMPOST)) {
            emiRegistry.addRecipe(new EmiCompostRecipe(recipe));
        }
        for (CrushingRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.CRUSHING)) {
            emiRegistry.addRecipe(new EmiCrushingRecipe(recipe));
        }
        for (HarvestRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.HARVEST)) {
            emiRegistry.addRecipe(new EmiHarvestingRecipe(recipe));
        }
        for (HeatRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.HEAT)) {
            emiRegistry.addRecipe(new EmiHeatRecipe(recipe));
        }
        for (MeltingRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.MELTING)) {
            emiRegistry.addRecipe(new EmiMeltingRecipe(recipe));
        }
        for (PrecipitateRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.PRECIPITATE)) {
            emiRegistry.addRecipe(new EmiPrecipitateRecipe(recipe));
        }
        for (SiftingRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.SIFTING)) {
            emiRegistry.addRecipe(new EmiSiftingRecipe(recipe));
        }
        for (SolidifyingRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.SOLIDIFYING)) {
            emiRegistry.addRecipe(new EmiSolidifyingRecipe(recipe));
        }
        for (TransitionRecipe recipe : manager.getAllRecipesFor(EXNRecipeTypes.TRANSITION)) {
            emiRegistry.addRecipe(new EmiTransitionRecipe(recipe));
        }
    }
}
