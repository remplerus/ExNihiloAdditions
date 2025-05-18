package com.rempler.exnihiloadditions.compat.emi.recipe;

import com.rempler.exnihiloadditions.compat.emi.EXNEMIPlugin;
import com.rempler.exnihiloadditions.compat.emi.widgets.BlockRenderWidget;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

import java.util.ArrayList;
import java.util.List;

public class EmiTransitionRecipe extends BasicEmiRecipe {
    private final List<BlockState> blockStates = new ArrayList<>();
    private final List<Component> blockComponents = new ArrayList<>();
    private final List<BlockState> BARREL_STATES = new ArrayList<>();

    public EmiTransitionRecipe(TransitionRecipe recipe) {
        super(EXNEMIPlugin.TRANSITION, recipe.getId(), 70, 35);
        this.inputs.add(EmiStack.of(recipe.getFluidInTank().getFluid()).setAmount(Config.getBarrelNumberOfBuckets() * 1000L));
        this.catalysts.add(EmiIngredient.of(recipe.getCatalyst()));
        this.outputs.add(EmiStack.of(recipe.getResult().getFluid()).setAmount(Config.getBarrelNumberOfBuckets() * 1000L));
        for (int i = 0;i < recipe.getCatalyst().getItems().length; i++) {
            if (recipe.getCatalyst().getItems()[i].getItem() instanceof BlockItem blockItem) {
                this.blockStates.add(blockItem.getBlock().defaultBlockState());
            }
        }
        if (recipe.getCatalyst().isSimple()) {
            this.blockComponents.add(Component.translatable(recipe.getCatalyst().getItems()[0].getDescriptionId()));
        }

        for (HolderSet.Named<Item> block : BuiltInRegistries.ITEM.getTag(ExNihiloTags.BARREL).stream().toList()) {
            for (Holder<Item> item : block) {
                if (item.get() instanceof BlockItem blockItem) {
                    BARREL_STATES.add(blockItem.getBlock().defaultBlockState());
                }
            }
        }
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTank(inputs.get(0), 0, 0, 18, 18, 1000);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 18, 1);
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 27, 1);
        widgetHolder.addTank(outputs.get(0), 51, 0, 18, 18, 1000).recipeContext(this);
        widgetHolder.add(new BlockRenderWidget(32, 6, BARREL_STATES, EXNEMIPlugin.BARRELS, 13f, null));
        widgetHolder.add(new BlockRenderWidget(32, 21, blockStates, blockComponents, 13f, null));
    }
}
