
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.client.gui.SchoolBellControllerGUIScreen;
import net.xbtstudio.school.client.gui.PrinterGuiScreen;
import net.xbtstudio.school.client.gui.MusicselectionpageScreen;
import net.xbtstudio.school.client.gui.BoxScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SchoolModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SchoolModMenus.BOX.get(), BoxScreen::new);
			MenuScreens.register(SchoolModMenus.SCHOOL_BELL_CONTROLLER_GUI.get(), SchoolBellControllerGUIScreen::new);
			MenuScreens.register(SchoolModMenus.PRINTER_GUI.get(), PrinterGuiScreen::new);
			MenuScreens.register(SchoolModMenus.MUSICSELECTIONPAGE.get(), MusicselectionpageScreen::new);
		});
	}
}
