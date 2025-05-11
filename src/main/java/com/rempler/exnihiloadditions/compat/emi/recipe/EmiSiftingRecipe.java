package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.novacore.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmiSiftingRecipe extends BasicEmiRecipe {
    public static final EmiTexture SIFTING_TEXTURE = new EmiTexture(EXNEMIPlugin.JEI_MID_SHEET, 0, 0, 166, 58);
    private final boolean isWaterlogged;

    public EmiSiftingRecipe(SiftingRecipe recipe) {
        super(EXNEMIPlugin.SIFTING, recipe.getId(), 166, 58);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
        this.outputs.add(EmiStack.of(recipe.getDrop()));
        this.isWaterlogged = recipe.isWaterlogged();
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        MeshType mesh = null;
        List<Component> tooltip = new ArrayList<>();
        widgetHolder.addSlot(inputs.get(0), 11, 3);

        for(int i = 0; i < outputs.size(); ++i) {
            int slotX = 39 + i % 7 * 18;
            int slotY = 3 + i / 7 * 18;
            Multiset<String> condensedTooltips = HashMultiset.create();

            for(SiftingRecipe entry : ExNihiloRegistries.SIEVE_REGISTRY.getDrops(((ItemStack)((List<?>)this.inputs.get(1)).get(0)).getItem(), ((MeshItem)((ItemStack)((List<?>)this.inputs.get(0)).get(0)).getItem()).getType(), this.isWaterlogged)) {
                for(MeshWithChance meshWithChance : entry.getRolls()) {
                    condensedTooltips.add(StringUtils.formatPercent(meshWithChance.getChance()));
                    mesh = meshWithChance.getMesh();
                }
            }
            if (!(mesh == null)) {
                widgetHolder.addSlot(EmiStack.of(MeshItem.getMesh(mesh)), 11, 39);
            }

            tooltip.add(Component.translatable("jei.sieve.dropChance"));

            for(String line : condensedTooltips.elementSet()) {
                int var10001 = condensedTooltips.count(line);
                tooltip.add(Component.literal(" * " + var10001 + "x " + line));
            }
            SlotWidget slot = widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this);

            for (int j = 0; j <= tooltip.size(); j++) {
                slot.appendTooltip(tooltip.get(j));
            }
        }
    }
}
