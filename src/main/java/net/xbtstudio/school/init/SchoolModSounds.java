
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
	public static final RegistryObject<SoundEvent> ATHLETEMARCH = REGISTRY.register("athletemarch", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "athletemarch")));
	public static final RegistryObject<SoundEvent> CLASSOVER_RING = REGISTRY.register("classover-ring", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "classover-ring")));
	public static final RegistryObject<SoundEvent> RFSHOOT = REGISTRY.register("rfshoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "rfshoot")));
	public static final RegistryObject<SoundEvent> SMGSHOOT = REGISTRY.register("smgshoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "smgshoot")));
	public static final RegistryObject<SoundEvent> RUNDRILLMUSIC = REGISTRY.register("rundrillmusic", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "rundrillmusic")));
	public static final RegistryObject<SoundEvent> COLORFULSUNSHINE = REGISTRY.register("colorfulsunshine", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "colorfulsunshine")));
	public static final RegistryObject<SoundEvent> EYEEXERCISES = REGISTRY.register("eyeexercises", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "eyeexercises")));
	public static final RegistryObject<SoundEvent> DANCINGYOUTH = REGISTRY.register("dancingyouth", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "dancingyouth")));
	public static final RegistryObject<SoundEvent> ABLETENNISTABL = REGISTRY.register("abletennistabl", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "abletennistabl")));
	public static final RegistryObject<SoundEvent> HOW_DO_YOU_SLEEP = REGISTRY.register("how_do_you_sleep", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "how_do_you_sleep")));
	public static final RegistryObject<SoundEvent> DOCTOR_SOUND = REGISTRY.register("doctor_sound", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "doctor_sound")));
	public static final RegistryObject<SoundEvent> JUNE_1ST_CHILDREN_S_NETWORK = REGISTRY.register("june_1st_children_s_network", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("school", "june_1st_children_s_network")));
}
