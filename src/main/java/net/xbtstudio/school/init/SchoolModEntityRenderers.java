
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.client.renderer.ChalkSMGProjectileRenderer;
import net.xbtstudio.school.client.renderer.ChalkRFProjectileRenderer;
import net.xbtstudio.school.client.renderer.ChalkHairSprayerProjectileRenderer;
import net.xbtstudio.school.client.renderer.ChalkCannonProjectileRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SchoolModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(SchoolModEntities.CHALK_HAIR_SPRAYER_PROJECTILE.get(), ChalkHairSprayerProjectileRenderer::new);
		event.registerEntityRenderer(SchoolModEntities.CHALK_SMG_PROJECTILE.get(), ChalkSMGProjectileRenderer::new);
		event.registerEntityRenderer(SchoolModEntities.CHALK_RF_PROJECTILE.get(), ChalkRFProjectileRenderer::new);
		event.registerEntityRenderer(SchoolModEntities.CHALK_CANNON_PROJECTILE.get(), ChalkCannonProjectileRenderer::new);
	}
}
