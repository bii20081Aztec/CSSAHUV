
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.entity.ChalkSMGProjectileEntity;
import net.xbtstudio.school.entity.ChalkRFProjectileEntity;
import net.xbtstudio.school.entity.ChalkHairSprayerProjectileEntity;
import net.xbtstudio.school.entity.ChalkCannonProjectileEntity;
import net.xbtstudio.school.SchoolMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SchoolModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SchoolMod.MODID);
	public static final RegistryObject<EntityType<ChalkHairSprayerProjectileEntity>> CHALK_HAIR_SPRAYER_PROJECTILE = register("chalk_hair_sprayer_projectile",
			EntityType.Builder.<ChalkHairSprayerProjectileEntity>of(ChalkHairSprayerProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(ChalkHairSprayerProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ChalkSMGProjectileEntity>> CHALK_SMG_PROJECTILE = register("chalk_smg_projectile", EntityType.Builder.<ChalkSMGProjectileEntity>of(ChalkSMGProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(ChalkSMGProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ChalkRFProjectileEntity>> CHALK_RF_PROJECTILE = register("chalk_rf_projectile", EntityType.Builder.<ChalkRFProjectileEntity>of(ChalkRFProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(ChalkRFProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ChalkCannonProjectileEntity>> CHALK_CANNON_PROJECTILE = register("chalk_cannon_projectile", EntityType.Builder.<ChalkCannonProjectileEntity>of(ChalkCannonProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(ChalkCannonProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}
}
