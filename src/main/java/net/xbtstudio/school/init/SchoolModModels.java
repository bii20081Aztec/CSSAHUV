
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xbtstudio.school.init;

import net.xbtstudio.school.client.model.Modelchalks;
import net.xbtstudio.school.client.model.Modelchalk;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SchoolModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelchalks.LAYER_LOCATION, Modelchalks::createBodyLayer);
		event.registerLayerDefinition(Modelchalk.LAYER_LOCATION, Modelchalk::createBodyLayer);
	}
}
