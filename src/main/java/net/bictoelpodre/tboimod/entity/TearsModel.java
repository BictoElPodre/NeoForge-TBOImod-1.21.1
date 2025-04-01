package net.bictoelpodre.tboimod.entity;// Made with Blockbench 4.12.4
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
	private final ModelPart tear;

	public TearsModel(ModelPart root) {
		this.tear = root.getChild("tear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tear = partdefinition.addOrReplaceChild("tear", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -8.0F, -7.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(1.0F, -10.0F, -5.0F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 20).addBox(3.0F, -12.0F, -3.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(3.0F, -10.0F, -7.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 36).addBox(3.0F, -10.0F, 3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 48).addBox(5.0F, -12.0F, -5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 52).addBox(5.0F, -12.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 40).addBox(3.0F, 0.0F, -7.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(1.0F, 0.0F, -5.0F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 44).addBox(3.0F, 0.0F, 3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 26).addBox(3.0F, 2.0F, -3.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 54).addBox(5.0F, 2.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 56).addBox(5.0F, 2.0F, -5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(5.0F, -8.0F, 5.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(9.0F, -6.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 58).addBox(3.0F, -6.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 10).addBox(5.0F, -8.0F, -9.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 58).addBox(9.0F, -6.0F, -9.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(3.0F, -6.0F, -9.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(13.0F, -6.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 48).addBox(13.0F, -8.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(13.0F, -2.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 40).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(12, 52).addBox(-1.0F, -8.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 52).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 20.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		tear.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public void setupAnim(TearsEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
}