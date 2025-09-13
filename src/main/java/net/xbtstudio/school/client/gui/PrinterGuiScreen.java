package net.xbtstudio.school.client.gui;

import net.xbtstudio.school.world.inventory.PrinterGuiMenu;
import net.xbtstudio.school.network.PrinterGuiButtonMessage;
import net.xbtstudio.school.SchoolMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PrinterGuiScreen extends AbstractContainerScreen<PrinterGuiMenu> {
	private final static HashMap<String, Object> guistate = PrinterGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_print;

	public PrinterGuiScreen(PrinterGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 128;
	}

	private static final ResourceLocation texture = new ResourceLocation("school:textures/screens/printer_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("school:textures/screens/cross.png"), this.leftPos + 53, this.topPos + 21, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.school.printer_gui.label_empty"), 8, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_print = Button.builder(Component.translatable("gui.school.printer_gui.button_print"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new PrinterGuiButtonMessage(0, x, y, z));
				PrinterGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 118, this.topPos + 20, 51, 20).build();
		guistate.put("button:button_print", button_print);
		this.addRenderableWidget(button_print);
	}
}
