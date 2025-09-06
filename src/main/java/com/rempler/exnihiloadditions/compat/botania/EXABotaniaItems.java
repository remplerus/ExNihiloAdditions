package com.rempler.exnihiloadditions.compat.botania;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.botania.item.ElementiumCrook;
import com.rempler.exnihiloadditions.compat.botania.item.ElementiumHammer;
import com.rempler.exnihiloadditions.compat.botania.item.TerrasteelCrook;
import com.rempler.exnihiloadditions.compat.botania.item.TerrasteelHammer;
import net.minecraft.world.item.Tiers;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import vazkii.botania.api.BotaniaAPI;

import java.util.List;

public class EXABotaniaItems {
    public static ItemRegistry ITEMS = new ItemRegistry(ExNihiloAdditions.MODID);
    public static final ItemDefinition<CrookItem> livingwoodCrook = ITEMS.item("Livingwood Crook", "livingwood_crook",
            () -> new CrookItem(Tiers.WOOD, EXABotaniaConfig.getCrookLivingwoodValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<CrookItem> dreamwoodCrook = ITEMS.item("Dreamwood Crook", "dreamwood_crook",
            () -> new CrookItem(Tiers.WOOD, EXABotaniaConfig.getCrookDreamwoodValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<CrookItem> livingrockCrook = ITEMS.item("Livingrock Crook", "livingrock_crook",
            () -> new CrookItem(Tiers.STONE, EXABotaniaConfig.getCrookLivingrockValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<HammerItem> livingwoodHammer = ITEMS.item("Livingwood Hammer", "livingwood_hammer",
            () -> new HammerItem(Tiers.WOOD, EXABotaniaConfig.getHammerLivingwoodValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<HammerItem> dreamwoodHammer = ITEMS.item("Dreamwood Hammer", "dreamwood_hammer",
            () -> new HammerItem(Tiers.WOOD, EXABotaniaConfig.getHammerDreamwoodValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<HammerItem> livingrockHammer = ITEMS.item("Livingrock Hammer", "livingrock_hammer",
            () -> new HammerItem(Tiers.STONE, EXABotaniaConfig.getHammerLivingrockValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<CrookItem> manasteelCrook = ITEMS.item("Manasteel Crook", "manasteel_crook",
            () -> new CrookItem(BotaniaAPI.instance().getManasteelItemTier(), EXABotaniaConfig.getCrookManasteelValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<ElementiumCrook> elementiumCrook = ITEMS.item("Elementium Crook", "elementium_crook",
            () -> new ElementiumCrook(BotaniaAPI.instance().getElementiumItemTier(), EXABotaniaConfig.getCrookElementiumValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<TerrasteelCrook> terrasteelCrook = ITEMS.item("Terrasteel Crook", "terrasteel_crook",
            () -> new TerrasteelCrook(BotaniaAPI.instance().getTerrasteelItemTier(), EXABotaniaConfig.getCrookTerrasteelValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<HammerItem> manasteelHammer = ITEMS.item("Manasteel Hammer", "manasteel_hammer",
            () -> new HammerItem(BotaniaAPI.instance().getManasteelItemTier(), EXABotaniaConfig.getHammerManasteelValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<ElementiumHammer> elementiumHammer = ITEMS.item("Elementium Hammer", "elementium_hammer",
            () -> new ElementiumHammer(BotaniaAPI.instance().getElementiumItemTier(), EXABotaniaConfig.getHammerElementiumValue()), ItemDefinition.ItemType.TOOL);
    public static final ItemDefinition<TerrasteelHammer> terrasteelHammer = ITEMS.item("Terrasteel Hammer", "terrasteel_hammer",
            () -> new TerrasteelHammer(BotaniaAPI.instance().getTerrasteelItemTier(), EXABotaniaConfig.getHammerTerrasteelValue()), ItemDefinition.ItemType.TOOL);

    public static List<ItemDefinition<?>> getDefinitions() {
        return ITEMS.getRegistry();
    }

}
