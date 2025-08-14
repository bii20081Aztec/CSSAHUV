package net.xbtstudio.school.client.renderer;

import net.xbtstudio.school.entity.ChalkHairSprayerProjectileEntity;
import net.xbtstudio.school.client.model.Modelchalk;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ChalkHairSprayerProjectileRenderer extends EntityRenderer<ChalkHairSprayerProjectileEntity> {
	private static final ResourceLocation texture = new ResourceLocation("school:textures/entities/chalk.png");
	private final Modelchalk model;

	public ChalkHairSprayerProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelchalk(context.bakeLayer(Modelchalk.LAYER_LOCATION));
	}

	@Override
	public void render(ChalkHairSprayerProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(ChalkHairSprayerProjectileEntity entity) {
		return texture;
	}
}
