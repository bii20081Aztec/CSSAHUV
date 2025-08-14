
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.world.inventory.SchoolBellControllerGUIMenu;
import net.xbtstudio.school.world.inventory.PrinterGuiMenu;
import net.xbtstudio.school.world.inventory.MusicselectionpageMenu;
import net.xbtstudio.school.world.inventory.BoxMenu;
import net.xbtstudio.school.SchoolMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class SchoolModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SchoolMod.MODID);
	public static final RegistryObject<MenuType<BoxMenu>> BOX = REGISTRY.register("box", () -> IForgeMenuType.create(BoxMenu::new));
	public static final RegistryObject<MenuType<SchoolBellControllerGUIMenu>> SCHOOL_BELL_CONTROLLER_GUI = REGISTRY.register("school_bell_controller_gui", () -> IForgeMenuType.create(SchoolBellControllerGUIMenu::new));
	public static final RegistryObject<MenuType<PrinterGuiMenu>> PRINTER_GUI = REGISTRY.register("printer_gui", () -> IForgeMenuType.create(PrinterGuiMenu::new));
	public static final RegistryObject<MenuType<MusicselectionpageMenu>> MUSICSELECTIONPAGE = REGISTRY.register("musicselectionpage", () -> IForgeMenuType.create(MusicselectionpageMenu::new));
}
