package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmiSiftingRecipe extends AbstractEmiRecipe {
    private final boolean isWaterlogged;
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> outputs = new ArrayList<>();
    private final List<MeshType> meshOrder;
    private final List<MeshType> meshTypes = new ArrayList<>();
    private final int displayWidth;
    private final int displayHeight;

    public EmiSiftingRecipe(SiftingRecipe recipe) {
        super(recipe);
        this.inputs.add(EmiIngredient.of(recipe.getInput()));
            this.isWaterlogged = recipe.isWaterlogged();

            this.meshOrder = List.of(
                    MeshType.STRING, MeshType.FLINT, MeshType.IRON,
                    MeshType.DIAMOND, MeshType.EMERALD, MeshType.NETHERITE
            );

        Map<MeshType, List<MeshWithChance>> groupedDrops = recipe.getRolls()
                .stream().collect(Collectors.groupingBy(MeshWithChance::getMesh));

        for (MeshType mesh : meshOrder) {
            if (groupedDrops.containsKey(mesh)) {
                float chance = 0f;
                for (MeshWithChance drop : groupedDrops.get(mesh)) {
                    chance += drop.getChance();
                }
                outputs.add(EmiStack.of(recipe.getDrop()).setChance(chance));
                meshTypes.add(mesh);
            }
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
            }
        }
        this.displayHeight = y;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return isWaterlogged ? EXNEMIPlugin.WATER_SIFTING : EXNEMIPlugin.SIFTING;
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
        widgetHolder.addSlot(inputs.get(0), 0, y / 2);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 19, y / 2 + 1);

        for (int i = 0; i < outputs.size(); i++) {
            int slotX = 43 + (i % 7) * 18;
            int slotY = (i / 7) * 18;
            widgetHolder.addSlot(outputs.get(i), slotX, slotY).recipeContext(this);

            MeshType meshForOutput = (meshTypes.size() == outputs.size())
                    ? meshTypes.get(i)
                    : meshTypes.get(i % meshTypes.size());

            if (Config.flattenSieveRecipes()) {
                int startIndex = 0;
                for (int z = 0; z < meshOrder.size(); z++) {
                    if (meshOrder.get(z).getMeshName().equals(meshForOutput.getMeshName())) {
                        startIndex = z;
                        break;
                    }
                }
                List<EmiIngredient> meshItems = meshOrder.subList(startIndex, meshOrder.size())
                        .stream()
                        .map(mesh -> EmiStack.of(MeshItem.getMesh(mesh)))
                        .collect(Collectors.toList());

                widgetHolder.addSlot(EmiIngredient.of(meshItems), slotX, slotY + 20).catalyst(true);
            } else {
                widgetHolder.addSlot(EmiStack.of(MeshItem.getMesh(meshForOutput)), slotX, slotY + 20).catalyst(true);
            }
        }
    }
}