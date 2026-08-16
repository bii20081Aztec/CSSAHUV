package net.xbtstudio.school.client.gui;

import net.xbtstudio.school.world.inventory.SNEMenu;
import net.xbtstudio.school.network.SNEButtonMessage;
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

public class SNEScreen extends AbstractContainerScreen<SNEMenu> {
	private final static HashMap<String, Object> guistate = SNEMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_forget_the_time;
	Button button_my_future_is_not_dream;
	Button button_conquer;

	public SNEScreen(SNEMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("school:textures/screens/sne.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.school.sne.label_school_nap_bell_expansion_panel"), 7, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_forget_the_time = Button.builder(Component.translatable("gui.school.sne.button_forget_the_time"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SNEButtonMessage(0, x, y, z));
				SNEButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 29, 103, 20).build();
		guistate.put("button:button_forget_the_time", button_forget_the_time);
		this.addRenderableWidget(button_forget_the_time);
		button_my_future_is_not_dream = Button.builder(Component.translatable("gui.school.sne.button_my_future_is_not_dream"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SNEButtonMessage(1, x, y, z));
				SNEButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 52, 139, 20).build();
		guistate.put("button:button_my_future_is_not_dream", button_my_future_is_not_dream);
		this.addRenderableWidget(button_my_future_is_not_dream);
		button_conquer = Button.builder(Component.translatable("gui.school.sne.button_conquer"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SNEButtonMessage(2, x, y, z));
				SNEButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 75, 61, 20).build();
		guistate.put("button:button_conquer", button_conquer);
		this.addRenderableWidget(button_conquer);
	}
}
