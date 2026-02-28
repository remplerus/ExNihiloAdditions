package com.rempler.exnihiloadditions.compat.rei.display;

import com.rempler.exnihiloadditions.compat.rei.EXAREIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class REISiftingDisplay extends BasicDisplay {
    private final SiftingRecipe recipe;
    private final boolean isWaterlogged;
    private final List<MeshType> meshTypes = new ArrayList<>();
    private final List<Float> chances = new ArrayList<>();

    private static final List<MeshType> MESH_ORDER = List.of(
            MeshType.STRING, MeshType.FLINT, MeshType.IRON,
            MeshType.DIAMOND, MeshType.EMERALD, MeshType.NETHERITE
    );

    public REISiftingDisplay(SiftingRecipe recipe) {
        super(
                List.of(EntryIngredients.ofIngredient(recipe.getInput())),
                buildOutputs(recipe)
        );
        this.recipe = recipe;
        this.isWaterlogged = recipe.isWaterlogged();

        Map<MeshType, List<MeshWithChance>> groupedDrops = recipe.getRolls()
                .stream().collect(Collectors.groupingBy(MeshWithChance::getMesh));

        for (MeshType mesh : MESH_ORDER) {
            if (groupedDrops.containsKey(mesh)) {
                meshTypes.add(mesh);
                float totalChance = 0f;
                for (MeshWithChance drop : groupedDrops.get(mesh)) {
                    totalChance += drop.getChance();
                }
                chances.add(totalChance);
            }
        }
    }

    /**
     * Build the actual output entries - these are the DROPPED ITEMS, not the meshes.
     * Each mesh type that has drops produces one output entry with the drop's ItemStack.
     */
    private static List<EntryIngredient> buildOutputs(SiftingRecipe recipe) {
        List<EntryIngredient> outputs = new ArrayList<>();

        Map<MeshType, List<MeshWithChance>> groupedDrops = recipe.getRolls()
                .stream().collect(Collectors.groupingBy(MeshWithChance::getMesh));

        for (MeshType mesh : MESH_ORDER) {
            if (groupedDrops.containsKey(mesh)) {
                // The output is the actual drop item, NOT the mesh
                outputs.add(EntryIngredients.of(recipe.getDrop()));
            }
        }

        return outputs;
    }

    public SiftingRecipe getRecipe() {
        return recipe;
    }

    public boolean isWaterlogged() {
        return isWaterlogged;
    }

    public List<MeshType> getMeshTypes() {
        return meshTypes;
    }

    public List<Float> getChances() {
        return chances;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return isWaterlogged ? EXAREIPlugin.WET_SIFTING : EXAREIPlugin.DRY_SIFTING;
    }
}
