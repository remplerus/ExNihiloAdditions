package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.compat.jei.sifting.JEISieveRecipe;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EmiSiftingRecipe implements EmiRecipe {
    private final boolean isWaterlogged;
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private List<EmiStack> outputs = new ArrayList<>();
    private final EmiStack meshType;
    private final int displayWidth;
    private final int displayHeight;
    private final ResourceLocation id;

    public EmiSiftingRecipe(ResourceLocation rl, JEISieveRecipe recipe, boolean isWaterlogged) {
        this.id = rl;
        this.inputs.add(EmiStack.of(recipe.getInputs().get(1).get(0)));
        this.meshType = EmiStack.of(recipe.getMesh());
        this.isWaterlogged = isWaterlogged;

        for(SiftingRecipe entry : ExNihiloRegistries.SIEVE_REGISTRY.getDrops(((ItemStack)((List)recipe.getInputs().get(1)).get(0)).getItem(), ((MeshItem)((ItemStack)((List)recipe.getInputs().get(0)).get(0)).getItem()).getType(), this.isWaterlogged)) {
            ItemStack drop = entry.getDrop();
            float chance = 0;
            for(MeshWithChance meshWithChance : entry.getRolls()) {
                chance += meshWithChance.getChance();
            }
            this.outputs.add(EmiStack.of(drop).setChance(chance));
        }

        int x = 187;
        switch (outputs.size()) {
            case 1 -> x = x - 7 * 18;
            case 2 -> x = x - 6 * 18;
            case 3 -> x = x - 5 * 18;
            case 4 -> x = x - 4 * 18;
            case 5 -> x = x - 3 * 18;
            case 6 -> x = x - 2 * 18;
            case 7 -> x = x - 18;
            default -> {}
        }
        this.displayWidth = x;

        int y = 40;
        if (outputs.size() > 7) {
            if (outputs.size() <= 14) {
                y += 18;
            } else if (outputs.size() <= 21) {
                y += 2 * 18;
            } else if (outputs.size() <= 28) {
                y += 3 * 18;
            } else if (outputs.size() <= 35) {
                y += 4 * 18;
            }
        }
        this.displayHeight = y;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return isWaterlogged ? EXNEMIPlugin.WATER_SIFTING : EXNEMIPlugin.SIFTING;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return displayWidth;
    }

    @Override
    public int getDisplayHeight() {
        return displayHeight;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        int y = getDisplayHeight() - 18;
        widgetHolder.addSlot(inputs.get(0), 0, 0);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 19, y / 2 + 1);

        for (int i = 0; i < outputs.size(); i++) {
            int slotX = 43 + (i % 7) * 18;
            int slotY = (i / 7) * 18;
            widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this);
        }

        widgetHolder.addSlot(meshType, 0, y-1).catalyst(true);
    }
}