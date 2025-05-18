package com.rempler.exnihiloadditions.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.minecraft.world.item.Item;
import com.rempler.exnihiloadditions.compat.rei.compost.CompostRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.compost.CompostRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.crushing.CrushingRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.crushing.CrushingRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.harvest.HarvestRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.harvest.HarvestRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.heat.HeatRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.heat.HeatRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.melting.MeltingRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.melting.MeltingRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.precipitate.PrecipitateRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.precipitate.PrecipitateRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.sifting.SiftingRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.sifting.SiftingRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.solidifying.SolidifyingRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.solidifying.SolidifyingRecipeDisplay;
import com.rempler.exnihiloadditions.compat.rei.transition.TransitionRecipeCategory;
import com.rempler.exnihiloadditions.compat.rei.transition.TransitionRecipeDisplay;
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
import novamachina.novacore.world.item.ItemDefinition;

@REIPluginClient
public class EXNREIClientPlugin implements REIClientPlugin {

    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CompostRecipeCategory());
        registry.add(new CrushingRecipeCategory());
        registry.add(new HarvestRecipeCategory());
        registry.add(new HeatRecipeCategory());
        registry.add(new MeltingRecipeCategory());
        registry.add(new PrecipitateRecipeCategory());
        registry.add(new SiftingRecipeCategory());
        registry.add(new SolidifyingRecipeCategory());
        registry.add(new TransitionRecipeCategory());

        for (ItemDefinition<? extends Item> item : EXNBlocks.getDefinitions()) {
            if (item.getEnglishName().toLowerCase().contains("barrel")) {
                registry.addWorkstations(CompostRecipeCategory.COMPOSTING, EntryStacks.of(item));
                registry.addWorkstations(PrecipitateRecipeCategory.PRECIPITATE, EntryStacks.of(item));
                registry.addWorkstations(SolidifyingRecipeCategory.SOLIDIFY, EntryStacks.of(item));
                registry.addWorkstations(TransitionRecipeCategory.TRANSITION, EntryStacks.of(item));
            }
            if (item.getEnglishName().toLowerCase().contains("crucible")) {
                registry.addWorkstations(MeltingRecipeCategory.MELTING, EntryStacks.of(item));
                registry.addWorkstations(HeatRecipeCategory.HEATING, EntryStacks.of(item));
            }
            if (item.getEnglishName().toLowerCase().contains("sieve")) {
                registry.addWorkstations(SiftingRecipeCategory.SIFTING, EntryStacks.of(item));
            }
        }
        for (ItemDefinition<? extends Item> item : EXNItems.getDefinitions()) {
            if (item.getEnglishName().toLowerCase().contains("hammer")) {
                registry.addWorkstations(CrushingRecipeCategory.CRUSHING, EntryStacks.of(item));
            }
            if (item.getEnglishName().toLowerCase().contains("crook")) {
                registry.addWorkstations(HarvestRecipeCategory.HARVESTING, EntryStacks.of(item));
            }
        }
    }

    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CompostRecipe.class, EXNRecipeTypes.COMPOST, CompostRecipeDisplay::new);
        registry.registerRecipeFiller(CrushingRecipe.class, EXNRecipeTypes.CRUSHING, CrushingRecipeDisplay::new);
        registry.registerRecipeFiller(HarvestRecipe.class, EXNRecipeTypes.HARVEST, HarvestRecipeDisplay::new);
        registry.registerRecipeFiller(HeatRecipe.class, EXNRecipeTypes.HEAT, HeatRecipeDisplay::new);
        registry.registerRecipeFiller(MeltingRecipe.class, EXNRecipeTypes.MELTING, MeltingRecipeDisplay::new);
        registry.registerRecipeFiller(PrecipitateRecipe.class, EXNRecipeTypes.PRECIPITATE, PrecipitateRecipeDisplay::new);
        registry.registerRecipeFiller(SiftingRecipe.class, EXNRecipeTypes.SIFTING, SiftingRecipeDisplay::new);
        registry.registerRecipeFiller(SolidifyingRecipe.class, EXNRecipeTypes.SOLIDIFYING, SolidifyingRecipeDisplay::new);
        registry.registerRecipeFiller(TransitionRecipe.class, EXNRecipeTypes.TRANSITION, TransitionRecipeDisplay::new);
    }
}
