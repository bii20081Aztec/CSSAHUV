package net.xbtstudio.school.client.gui;

import net.xbtstudio.school.world.inventory.NOCATMenu;
import net.xbtstudio.school.network.NOCATButtonMessage;
import net.xbtstudio.school.SchoolMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class NOCATScreen extends AbstractContainerScreen<NOCATMenu> {
	private final static HashMap<String, Object> guistate = NOCATMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_rebirth;

	public NOCATScreen(NOCATMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 0;
		this.imageHeight = 0;
	}

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
		guiGraphics.drawString(this.font, Component.translatable("gui.school.nocat.label_you_have_been_attacked_by_a_cat"), -159, -91, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.school.nocat.label_and_you_can_find_a_way_to_do_it"), -111, -76, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.school.nocat.label_you_still_want_to_be_reborn_dre"), -147, 37, -3407872, false);
	}

	@Override
	public void init() {
		super.init();
		button_rebirth = Button.builder(Component.translatable("gui.school.nocat.button_rebirth"), e -> {
			if (true) {
				SchoolMod.PACKET_HANDLER.sendToServer(new NOCATButtonMessage(0, x, y, z));
				NOCATButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + -25, this.topPos + -17, 61, 20).build();
		guistate.put("button:button_rebirth", button_rebirth);
		this.addRenderableWidget(button_rebirth);
	}
}
