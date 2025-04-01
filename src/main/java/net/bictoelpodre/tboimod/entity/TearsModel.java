package net.bictoelpodre.tboimod.entity;
// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TearsModel extends EntityModel<TearsEntity> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "tears_entity"), "main");
	private final ModelPart bone;

	public TearsModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-21.0F, -2.0F, -16.0F, 12.0F, 2.0F, 32.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-25.0F, -2.0F, -14.0F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-9.0F, -2.0F, -14.0F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(64, 34).addBox(-27.0F, -2.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(64, 60).addBox(-5.0F, -2.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(64, 86).addBox(-29.0F, -2.0F, -10.0F, 2.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(88, 0).addBox(-3.0F, -2.0F, -10.0F, 2.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 94).addBox(-31.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(28, 94).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(15.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 144, 144);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public void setupAnim(TearsEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
}