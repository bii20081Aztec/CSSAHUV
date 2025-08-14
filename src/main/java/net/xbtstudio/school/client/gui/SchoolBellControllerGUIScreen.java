package net.xbtstudio.school.client.gui;

import net.xbtstudio.school.world.inventory.SchoolBellControllerGUIMenu;
import net.xbtstudio.school.network.SchoolBellControllerGUIButtonMessage;
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

public class SchoolBellControllerGUIScreen extends AbstractContainerScreen<SchoolBellControllerGUIMenu> {
	private final static HashMap<String, Object> guistate = SchoolBellControllerGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_class_beginover;
	Button button_go_downstairs;
	Button button_stop_all_sound;
	Button button_dancing_youth;
	Button button_colorful_sunshine;
	Button button_eye_exercises;
	Button button_running_drills;
	Button button_shut_down;

	public SchoolBellControllerGUIScreen(SchoolBellControllerGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 220;
		this.imageHeight = 140;
	}

	private static final ResourceLocation texture = new ResourceLocation("school:textures/screens/school_bell_controller_gui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.school.school_bell_controller_gui.label_school_bell_controller"), 10, 6, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.school.school_bell_controller_gui.label_modified_based_on_the_rabbit_ver"), 7, 120, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_class_beginover = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_class_beginover"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(0, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 92, 93, 20).build();
		guistate.put("button:button_class_beginover", button_class_beginover);
		this.addRenderableWidget(button_class_beginover);
		button_go_downstairs = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_go_downstairs"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(1, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 92, 113, 20).build();
		guistate.put("button:button_go_downstairs", button_go_downstairs);
		this.addRenderableWidget(button_go_downstairs);
		button_stop_all_sound = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_stop_all_sound"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(2, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 22, 120, 20).build();
		guistate.put("button:button_stop_all_sound", button_stop_all_sound);
		this.addRenderableWidget(button_stop_all_sound);
		button_dancing_youth = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_dancing_youth"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(3, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 69, 93, 20).build();
		guistate.put("button:button_dancing_youth", button_dancing_youth);
		this.addRenderableWidget(button_dancing_youth);
		button_colorful_sunshine = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_colorful_sunshine"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(4, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 69, 113, 20).build();
		guistate.put("button:button_colorful_sunshine", button_colorful_sunshine);
		this.addRenderableWidget(button_colorful_sunshine);
		button_eye_exercises = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_eye_exercises"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(5, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 4, this.topPos + 46, 93, 20).build();
		guistate.put("button:button_eye_exercises", button_eye_exercises);
		this.addRenderableWidget(button_eye_exercises);
		button_running_drills = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_running_drills"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(6, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 99, this.topPos + 46, 113, 20).build();
		guistate.put("button:button_running_drills", button_running_drills);
		this.addRenderableWidget(button_running_drills);
		button_shut_down = Button.builder(Component.translatable("gui.school.school_bell_controller_gui.button_shut_down"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new SchoolBellControllerGUIButtonMessage(7, x, y, z));
				SchoolBellControllerGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 160, this.topPos + 4, 55, 20).build();
		guistate.put("button:button_shut_down", button_shut_down);
		this.addRenderableWidget(button_shut_down);
	}
}
