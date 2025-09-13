package net.xbtstudio.school.client.gui;

import net.xbtstudio.school.world.inventory.MusicselectionpageMenu;
import net.xbtstudio.school.network.MusicselectionpageButtonMessage;
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

public class MusicselectionpageScreen extends AbstractContainerScreen<MusicselectionpageMenu> {
	private final static HashMap<String, Object> guistate = MusicselectionpageMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_shut_down;
	Button button_blind_date_you_love_each_other;
	Button button_welcome_to_beijing;
	Button button_embrace_spring;
	Button button_unafraid;
	Button button_mexican_folk_songs;
	Button button_naughty_blue_cat;
	Button button_stop_the_audio;

	public MusicselectionpageScreen(MusicselectionpageMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 260;
		this.imageHeight = 120;
	}

	private static final ResourceLocation texture = new ResourceLocation("school:textures/screens/musicselectionpage.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.school.musicselectionpage.label_school_bell_controller"), 6, 3, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_shut_down = Button.builder(Component.translatable("gui.school.musicselectionpage.button_shut_down"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(0, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 188, this.topPos + 18, 55, 20).build();
		guistate.put("button:button_shut_down", button_shut_down);
		this.addRenderableWidget(button_shut_down);
		button_blind_date_you_love_each_other = Button.builder(Component.translatable("gui.school.musicselectionpage.button_blind_date_you_love_each_other"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(1, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 18, 181, 20).build();
		guistate.put("button:button_blind_date_you_love_each_other", button_blind_date_you_love_each_other);
		this.addRenderableWidget(button_blind_date_you_love_each_other);
		button_welcome_to_beijing = Button.builder(Component.translatable("gui.school.musicselectionpage.button_welcome_to_beijing"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(2, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 7, this.topPos + 64, 119, 20).build();
		guistate.put("button:button_welcome_to_beijing", button_welcome_to_beijing);
		this.addRenderableWidget(button_welcome_to_beijing);
		button_embrace_spring = Button.builder(Component.translatable("gui.school.musicselectionpage.button_embrace_spring"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(3, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 28, this.topPos + 41, 98, 20).build();
		guistate.put("button:button_embrace_spring", button_embrace_spring);
		this.addRenderableWidget(button_embrace_spring);
		button_unafraid = Button.builder(Component.translatable("gui.school.musicselectionpage.button_unafraid"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(4, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 59, this.topPos + 86, 67, 20).build();
		guistate.put("button:button_unafraid", button_unafraid);
		this.addRenderableWidget(button_unafraid);
		button_mexican_folk_songs = Button.builder(Component.translatable("gui.school.musicselectionpage.button_mexican_folk_songs"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(5, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 127, this.topPos + 41, 119, 20).build();
		guistate.put("button:button_mexican_folk_songs", button_mexican_folk_songs);
		this.addRenderableWidget(button_mexican_folk_songs);
		button_naughty_blue_cat = Button.builder(Component.translatable("gui.school.musicselectionpage.button_naughty_blue_cat"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(6, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 127, this.topPos + 64, 108, 20).build();
		guistate.put("button:button_naughty_blue_cat", button_naughty_blue_cat);
		this.addRenderableWidget(button_naughty_blue_cat);
		button_stop_the_audio = Button.builder(Component.translatable("gui.school.musicselectionpage.button_stop_the_audio"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new MusicselectionpageButtonMessage(7, x, y, z));
				MusicselectionpageButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 127, this.topPos + 86, 98, 20).build();
		guistate.put("button:button_stop_the_audio", button_stop_the_audio);
		this.addRenderableWidget(button_stop_the_audio);
	}
}
