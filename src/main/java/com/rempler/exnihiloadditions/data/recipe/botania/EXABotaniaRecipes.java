package com.rempler.exnihiloadditions.data.recipe.botania;

import com.rempler.exnihiloadditions.compat.botania.EXABotaniaItems;
import com.rempler.exnihiloadditions.data.recipe.EXARecipeHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import novamachina.novacore.world.item.ItemDefinition;

import java.util.function.Consumer;

public class EXABotaniaRecipes {
    private static final String modId = "botania";

    public static void init(Consumer<FinishedRecipe> consumer) {
        for (ItemDefinition<?> item : EXABotaniaItems.getDefinitions()) {
            String itemName1 = item.getEnglishName().replace(" ", "_").toLowerCase();
            String itemName;
            if (itemName1.contains("_crook")) {
                itemName = itemName1.replace("_crook", "");
            } else if (itemName1.contains("_hammer")) {
                itemName = itemName1.replace("_hammer", "");
            } else {
                continue;
            }
            if (item.asItem() instanceof CrookItem) {
                ItemDefinition<CrookItem> crook = (ItemDefinition<CrookItem>) item;
                itemName = getItemName(itemName);
                EXARecipeHelper.createCrook(consumer, crook,
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modId, itemName)), modId);
            } else if (item.asItem() instanceof HammerItem) {
                ItemDefinition<HammerItem> hammer = (ItemDefinition<HammerItem>) item;
                itemName = getItemName(itemName);
                EXARecipeHelper.createHammer(consumer, hammer,
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modId, itemName)), modId);
            }
        }
    }

    private static String getItemName(String itemName) {
        if (itemName.contains("wood")) {
            return itemName + "_planks";
        } else if (itemName.contains("livingrock")) {
            return itemName;
        } else if (itemName.contains("manasteel")) {
            return itemName + "_ingot";
        } else if (itemName.contains("elementium")) {
            return itemName + "_ingot";
        } else if (itemName.contains("terrasteel")) {
            return itemName + "_ingot";
        } else {
            return itemName;
        }
    }
}
