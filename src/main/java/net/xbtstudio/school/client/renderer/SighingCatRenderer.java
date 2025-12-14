
package net.xbtstudio.school.client.renderer;

import net.xbtstudio.school.entity.SighingCatEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.OcelotModel;

public class SighingCatRenderer extends MobRenderer<SighingCatEntity, OcelotModel<SighingCatEntity>> {
	public SighingCatRenderer(EntityRendererProvider.Context context) {
		super(context, new OcelotModel<SighingCatEntity>(context.bakeLayer(ModelLayers.OCELOT)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(SighingCatEntity entity) {
		return new ResourceLocation("school:textures/entities/red_cat_texture.png");
	}
}
