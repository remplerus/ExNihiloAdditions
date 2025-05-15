package com.rempler.exnihiloadditions.compat.emi.widgets;

import com.rempler.exnihiloadditions.compat.emi.client.EXAClientUtils;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.Widget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Predicate;

public class HeatWidget extends Widget {
    private final List<BlockState> states;
    private final List<Component> tooltips;
    private final int x;
    private final int y;
    private final float scale;
    private final Bounds bounds;

    public HeatWidget(int x, int y, List<BlockState> states, List<Component> tooltips, float scale) {
        this.x = x;
        this.y = y;
        this.states = states;
        this.tooltips = tooltips;
        this.scale = scale;

        int size = (int) (1.5 * scale);
        this.bounds = new Bounds(x - size / 2, y - size / 4, size, size);
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
        draw.pose().pushPose();

        int index = (int) (System.currentTimeMillis() / 1000 % states.size());
        BlockState current = this.states.get(index);

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
