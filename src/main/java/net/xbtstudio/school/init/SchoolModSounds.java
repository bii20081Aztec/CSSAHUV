
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.SchoolMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class SchoolModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SchoolMod.MODID);
	public static final RegistryObject<SoundEvent> SHOOT = REGISTRY.register("shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "shoot")));
	public static final RegistryObject<SoundEvent> SMGSHOOT = REGISTRY.register("smgshoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "smgshoot")));
	public static final RegistryObject<SoundEvent> ABLETENNISTABL = REGISTRY.register("abletennistabl", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "abletennistabl")));
}
