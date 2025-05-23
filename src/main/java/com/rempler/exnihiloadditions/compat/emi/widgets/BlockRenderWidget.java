package com.rempler.exnihiloadditions.compat.emi.widgets;

import com.rempler.exnihiloadditions.compat.emi.client.EXAClientUtils;
import dev.emi.emi.api.render.EmiRender;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.Widget;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;
import java.util.function.Predicate;

public class BlockRenderWidget extends Widget {
    private final List<BlockState> states;
    private final List<Component> tooltips;
    private final int x;
    private final int y;
    private final float scale;
    private final Bounds bounds;
    private final StatePropertiesPredicate properties;
    private boolean catalyst = false;

    public BlockRenderWidget(int x, int y, List<BlockState> states, List<Component> tooltips, float scale, StatePropertiesPredicate properties) {
        this.x = x;
        this.y = y;
        this.states = states;
        this.tooltips = tooltips;
        this.scale = scale;
        this.properties = properties;

        int size = (int) (1.5 * scale);
        this.bounds = new Bounds(x - size / 2, y - size / 4, size, size);
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

    public BlockRenderWidget catalyst(boolean catalyst) {
        this.catalyst = catalyst;
        return this;
    }

    @Override
    public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
        Bounds bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int xOff = (width - 16) / 2;
        int yOff = (height - 16) / 2;
        draw.pose().pushPose();

        if (states.isEmpty()) {
            states.add(Blocks.AIR.defaultBlockState());
        }
        int index = (int) (System.currentTimeMillis() / 1000 % states.size());
        BlockState current = this.states.get(index);
        if (!(properties == StatePropertiesPredicate.ANY) && current.getBlock() instanceof AbstractFurnaceBlock) {
            current = current.setValue(BlockStateProperties.LIT, true);
        }
        if (catalyst) {
            EmiRender.renderCatalystIcon(EmiStack.of(current.getBlock()), draw, this.x + xOff, this.y + yOff);
        }

        EXAClientUtils.renderBlock(draw, current, this.x, this.y, 0, this.scale);

        draw.pose().popPose();
    }

    @Override
    public List<ClientTooltipComponent> getTooltip(int mouseX, int mouseY) {
        return tooltips.stream()
                .map(component -> ClientTooltipComponent.create(component.getVisualOrderText()))
                .toList();
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        return slotCall(this.states, widget -> widget.mouseClicked(1, 1, button));
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return slotCall(this.states, widget -> widget.keyPressed(keyCode, scanCode, modifiers));
    }

    private static boolean slotCall(List<BlockState> states, Predicate<SlotWidget> callback) {
        int index = (int) (System.currentTimeMillis() / 1000 % states.size());
        BlockState current = states.get(index);
        EmiStack stack = EmiStack.of(current.getBlock());

        if (stack.isEmpty() && !current.getFluidState().isEmpty()) {
            stack = EmiStack.of(current.getFluidState().getType(), null, 1000);
        }
        if (stack.isEmpty()) {
            return false;
        } else {
            SlotWidget slot = new SlotWidget(stack, 0, 0);
            return callback.test(slot);
        }
    }
}
