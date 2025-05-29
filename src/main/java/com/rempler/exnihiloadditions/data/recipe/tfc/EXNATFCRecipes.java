package com.rempler.exnihiloadditions.data.recipe.tfc;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.compat.tfc.EXNATFCBlocks;
import com.rempler.exnihiloadditions.data.recipe.EXNARecipeHelper;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import novamachina.exnihilosequentia.data.recipes.SiftingRecipeBuilder;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.world.level.block.BlockDefinition;

import java.util.function.Consumer;

public class EXNATFCRecipes {
    private static final String modid = "tfc";

    public static void init(Consumer<FinishedRecipe> consumer) {
        createOreRecipes(consumer);
        for (BlockDefinition<?> blockDefinition : EXNATFCBlocks.getDefinitions()) {
            String blockName1 = blockDefinition.getEnglishName().replace(" ", "_").toLowerCase();
            String blockName;
            if (blockName1.contains("_crucible")) {
                blockName = blockName1.replace("_crucible", "");
            } else if (blockName1.contains("_barrel")) {
                blockName = blockName1.replace("_barrel", "");
            } else if (blockName1.contains("_sieve")) {
                blockName = blockName1.replace("_sieve", "");
            } else {
                continue;
            }
            if (blockDefinition.block() instanceof CrucibleBlock) {
                BlockDefinition<CrucibleBlock> crucible = (BlockDefinition<CrucibleBlock>) blockDefinition;
                EXNARecipeHelper.createCrucible(consumer, crucible,
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/lumber/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/planks/"+blockName+"_slab")),
                        modid);
            } else if (blockDefinition.block() instanceof BarrelBlock) {
                BlockDefinition<BarrelBlock> barrel = (BlockDefinition<BarrelBlock>) blockDefinition;
                EXNARecipeHelper.createBarrel(consumer, barrel,
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/planks/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/planks/"+blockName+"_slab")),
                        modid);
            } else if (blockDefinition.block() instanceof SieveBlock) {
                BlockDefinition<SieveBlock> sieve = (BlockDefinition<SieveBlock>) blockDefinition;
                EXNARecipeHelper.createSieve(consumer, sieve,
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/planks/"+blockName)),
                        BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "wood/planks/"+blockName+"_slab")),
                        modid);
            }
        }
    }

    public static void createOreRecipes(Consumer<FinishedRecipe> consumer) {
        for(Ore ore : Ore.values()) {
            if (ore.name().equals("LAPIS_LAZULI")) {
                createOreToTFC(consumer, ore, Items.LAPIS_LAZULI);
            } else if (ore.name().equals("EMERALD")) {
                createOreToTFC(consumer, ore, Items.EMERALD);
            } else if (ore.name().equals("DIAMOND")) {
                createOreToTFC(consumer, ore, Items.DIAMOND);
            } else if (ore.name().equals("AMETHYST")) {
                createOreToTFC(consumer, ore, Items.AMETHYST_SHARD);
            } else {
                Item input = Items.GRAVEL;
                if (ore.isGraded()) {
                    SiftingRecipeBuilder.sifting(input, BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "ore/poor_" + ore.name().toLowerCase())))
                            .addRoll(new MeshWithChance(MeshType.FLINT, 0.1f))
                            .build(consumer, ExNihiloAdditions.rl(modid+"/sifting/poor/" + ore.name().toLowerCase()));
                    input = Items.SAND;
                    SiftingRecipeBuilder.sifting(input, BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "ore/normal_" + ore.name().toLowerCase())))
                            .addRoll(new MeshWithChance(MeshType.FLINT, 0.1f))
                            .build(consumer, ExNihiloAdditions.rl(modid+"/sifting/normal/" + ore.name().toLowerCase()));
                    input = EXNBlocks.DUST.asItem();
                    SiftingRecipeBuilder.sifting(input, BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "ore/rich_" + ore.name().toLowerCase())))
                            .addRoll(new MeshWithChance(MeshType.FLINT, 0.1f))
                            .build(consumer, ExNihiloAdditions.rl(modid+"/sifting/rich/" + ore.name().toLowerCase()));
                } else {
                    SiftingRecipeBuilder.sifting(input, BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "ore/" + ore.name().toLowerCase())))
                            .addRoll(new MeshWithChance(MeshType.FLINT, 0.1f))
                            .build(consumer, ExNihiloAdditions.rl(modid+"/sifting/" + ore.name().toLowerCase()));
                }
            }
        }
    }

    private static void createOreToTFC(Consumer<FinishedRecipe> consumer, Ore ore, Item item) {
        ExNihiloAdditions.LOGGER.info(ore.name().toLowerCase());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BuiltInRegistries.ITEM.get(new ResourceLocation(modid, "gem/" + ore.name().toLowerCase())))
                .requires(item)
                .requires(TFCItems.SANDPAPER.get())
                .unlockedBy("has_sandpaper", InventoryChangeTrigger.TriggerInstance.hasItems(TFCItems.SANDPAPER.get()))
                .save(consumer, new ResourceLocation(ExNihiloAdditions.MODID, modid+"/sandpaper/vanilla_to_tfc_"+ore.name().toLowerCase()));
    }
}
