package com.rempler.exnihiloadditions.compat.emi.widgets;

import com.rempler.exnihiloadditions.compat.emi.client.EXAClientUtils;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.api.widget.WidgetTooltipHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderBuffers;

import java.util.List;
import java.util.function.BiFunction;

public class CompostWidget extends Widget implements WidgetTooltipHolder<CompostWidget> {
    protected final Bounds bounds;
    protected final int x, y;
    protected BiFunction<Integer, Integer, List<ClientTooltipComponent>> tooltipSupplier = (mouseX, mouseY) -> List.of();

    public CompostWidget() {
        this.x = 0;
        this.y = 0;
        this.bounds = new Bounds(x, y, 16, 16);
    }
    @Override
    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public CompostWidget tooltip(BiFunction<Integer, Integer, List<ClientTooltipComponent>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    @Override
    public List<ClientTooltipComponent> getTooltip(int mouseX, int mouseY) {
        return tooltipSupplier.apply(mouseX, mouseY);
    }

    public CompostWidget renderCompostBarrel() {
        GuiGraphics graphics = new GuiGraphics(Minecraft.getInstance(), new RenderBuffers().bufferSource());
        EXAClientUtils.renderCompostBarrel(graphics, x, y);
        this.render(graphics, x, y, 0);
        return this;
    }

    @Override
    public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
    }
}
