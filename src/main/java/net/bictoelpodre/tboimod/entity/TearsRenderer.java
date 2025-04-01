package net.bictoelpodre.tboimod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class TearsRenderer extends EntityRenderer<TearsEntity> {

    private TearsModel model;

    public TearsRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TearsModel(context.bakeLayer(TearsModel.LAYER_LOCATION));
    }

    @Override
    public void render(TearsEntity tears, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(tears)), false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(tears, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public Vec3 getRenderOffset(TearsEntity entity, float partialTicks) {
        return new Vec3(0.0, -1, 0.0);
    }

    @Override
    public ResourceLocation getTextureLocation(TearsEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/entity/tears_entity.png");
    }
}
