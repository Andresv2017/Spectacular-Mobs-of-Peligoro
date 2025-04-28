package net.darkblade.smopmod.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkblade.smopmod.entity.animations.ModAnimationDefinitions;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Hell_HippoModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Hipopotamo_Infernal;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart neck2;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart nose_hairs;
	private final ModelPart nose;
	private final ModelPart ears;
	private final ModelPart right_ear;
	private final ModelPart left_ear;
	private final ModelPart lower_jaw;
	private final ModelPart torso;
	private final ModelPart tail;
	private final ModelPart tailend;
	private final ModelPart legs;
	private final ModelPart front_legs;
	private final ModelPart right_leg1;
	private final ModelPart left_leg1;
	private final ModelPart back_legs;
	private final ModelPart right_leg2;
	private final ModelPart right_calf;
	private final ModelPart left_leg2;
	private final ModelPart left_calf;

	public Hell_HippoModel(ModelPart root) {
		this.Hipopotamo_Infernal = root.getChild("Hipopotamo_Infernal");
		this.body = this.Hipopotamo_Infernal.getChild("body");
		this.neck = this.body.getChild("neck");
		this.neck2 = this.neck.getChild("neck2");
		this.head = this.neck.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.nose_hairs = this.head.getChild("nose_hairs");
		this.nose = this.head.getChild("nose");
		this.ears = this.head.getChild("ears");
		this.right_ear = this.ears.getChild("right_ear");
		this.left_ear = this.ears.getChild("left_ear");
		this.lower_jaw = this.head.getChild("lower_jaw");
		this.torso = this.body.getChild("torso");
		this.tail = this.body.getChild("tail");
		this.tailend = this.tail.getChild("tailend");
		this.legs = this.Hipopotamo_Infernal.getChild("legs");
		this.front_legs = this.legs.getChild("front_legs");
		this.right_leg1 = this.front_legs.getChild("right_leg1");
		this.left_leg1 = this.front_legs.getChild("left_leg1");
		this.back_legs = this.legs.getChild("back_legs");
		this.right_leg2 = this.back_legs.getChild("right_leg2");
		this.right_calf = this.right_leg2.getChild("right_calf");
		this.left_leg2 = this.back_legs.getChild("left_leg2");
		this.left_calf = this.left_leg2.getChild("left_calf");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Hipopotamo_Infernal = partdefinition.addOrReplaceChild("Hipopotamo_Infernal", CubeListBuilder.create(), PartPose.offset(0.5F, 24.0F, 0.0F));

		PartDefinition body = Hipopotamo_Infernal.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 119).addBox(0.0F, -20.0F, -10.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 44).addBox(-9.0F, -15.0F, -15.0F, 18.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -20.0F, 18.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(118, 80).addBox(0.0F, -18.0F, -17.0F, 0.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -31.0F));

		PartDefinition neck2 = neck.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(76, 44).addBox(-5.0F, -10.0F, -14.0F, 10.0F, 16.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -5.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 84).addBox(-6.5F, -8.0F, -9.0F, 12.0F, 13.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(94, 111).addBox(-3.5F, -7.0F, -17.0F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(92, 19).addBox(-5.5F, -7.0F, -26.0F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(122, 35).addBox(-11.5F, -4.0F, -8.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(122, 35).mirror().addBox(5.5F, -4.0F, -8.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(38, 120).addBox(-4.5F, 0.0F, -25.0F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(108, 125).mirror().addBox(-4.5F, -10.0F, -23.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 119).addBox(-0.5F, -11.0F, -23.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(108, 125).addBox(3.5F, -10.0F, -23.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, -15.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(92, 35).addBox(-6.0F, -1.0F, -1.5F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(-0.5F, -5.0F, -7.5F));

		PartDefinition nose_hairs = head.addOrReplaceChild("nose_hairs", CubeListBuilder.create().texOffs(70, 107).mirror().addBox(-4.0F, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(130, 0).addBox(0.0F, -2.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(70, 107).addBox(4.0F, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.0F, -24.5F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(92, 39).addBox(-5.0F, -2.0F, -1.5F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -7.0F, -24.5F));

		PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -3.0F));

		PartDefinition right_ear = ears.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(128, 125).addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -1.5F, -1.0F));

		PartDefinition left_ear = ears.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(128, 125).mirror().addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, -1.5F, -1.0F));

		PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(92, 0).addBox(-3.0F, 0.0F, -10.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 106).addBox(-5.0F, 2.0F, -17.0F, 10.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(118, 98).addBox(-4.0F, -2.0F, -16.0F, 8.0F, 4.0F, 7.0F, new CubeDeformation(-0.01F))
		.texOffs(118, 41).addBox(-2.0F, 1.0F, -17.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(26, 129).mirror().addBox(-5.0F, 6.0F, -11.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 129).addBox(3.0F, 6.0F, -11.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, -9.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -10.0F, -16.0F, 22.0F, 20.0F, 24.0F, new CubeDeformation(0.0F))
				.texOffs(0, 140).addBox(-11.0F, -10.0F, -12.0F, 22.0F, 20.0F, 19.0F, new CubeDeformation(0.2F))
				.texOffs(0, 183).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -7.0F, -22.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(94, 125).addBox(-2.0F, 0.0F, -0.5F, 4.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 5.0F));

		PartDefinition tailend = tail.addOrReplaceChild("tailend", CubeListBuilder.create().texOffs(124, 109).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 11.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(70, 95).addBox(-1.0F, 0.0F, 2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(70, 101).addBox(1.0F, 1.0F, 2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 1.0F));

		PartDefinition legs = Hipopotamo_Infernal.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-0.5F, -20.0F, 0.0F));

		PartDefinition front_legs = legs.addOrReplaceChild("front_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -5.5F, -13.5F));

		PartDefinition right_leg1 = front_legs.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(70, 84).mirror().addBox(-2.5F, 10.0F, 3.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(42, 84).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 26.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.5F, -0.5F, 0.0F));

		PartDefinition left_leg1 = front_legs.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(70, 84).addBox(2.5F, 10.0F, 3.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 84).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 26.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(9.5F, -0.5F, 0.0F));

		PartDefinition back_legs = legs.addOrReplaceChild("back_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 18.0F));

		PartDefinition right_leg2 = back_legs.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(76, 80).mirror().addBox(-4.0F, 0.0F, -7.0F, 8.0F, 18.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.0F, 0.0F, 1.0F));

		PartDefinition right_calf = right_leg2.addOrReplaceChild("right_calf", CubeListBuilder.create().texOffs(70, 111).mirror().addBox(-2.5F, -4.0F, -2.0F, 5.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(120, 125).mirror().addBox(-0.5F, -6.0F, 4.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 17.0F, 4.0F));

		PartDefinition left_leg2 = back_legs.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(76, 80).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 18.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 1.0F));

		PartDefinition left_calf = left_leg2.addOrReplaceChild("left_calf", CubeListBuilder.create().texOffs(70, 111).addBox(-2.5F, -4.0F, -2.0F, 5.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(120, 125).addBox(0.5F, -6.0F, 4.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 17.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.Hell_HippoModelAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((Hell_HippoEntity) entity).idleAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.idle, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).attackAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.attack, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).walkAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.walk, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).swimAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.swim, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).sprintAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.sprint, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).waterIdleAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.widle, ageInTicks, 1f);
		this.animate(((Hell_HippoEntity) entity).eatAnimationState, ModAnimationDefinitions.Hell_HippoModelAnimation.eat, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Hipopotamo_Infernal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Hipopotamo_Infernal;
	}
}