package net.darkblade.smopmod.entity.client.tango;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkblade.smopmod.entity.animations.ModAnimationDefinitions;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class TangofteroModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart Rat;
    private final ModelPart body_parts;
    private final ModelPart neck;
    private final ModelPart epiglotis;
    private final ModelPart muscles;
    private final ModelPart upper_jaw;
    private final ModelPart eyes;
    private final ModelPart pupils;
    private final ModelPart arms;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail_tip;
    private final ModelPart legs;
    private final ModelPart right_leg;
    private final ModelPart right_calf;
    private final ModelPart right_foot;
    private final ModelPart left_leg;
    private final ModelPart left_calf;
    private final ModelPart left_foot;

    public TangofteroModel(ModelPart root) {
        this.Rat = root.getChild("Rat");
        this.body_parts = this.Rat.getChild("body_parts");
        this.neck = this.body_parts.getChild("neck");
        this.epiglotis = this.neck.getChild("epiglotis");
        this.muscles = this.neck.getChild("muscles");
        this.upper_jaw = this.neck.getChild("upper_jaw");
        this.eyes = this.upper_jaw.getChild("eyes");
        this.pupils = this.eyes.getChild("pupils");
        this.arms = this.body_parts.getChild("arms");
        this.right_arm = this.arms.getChild("right_arm");
        this.left_arm = this.arms.getChild("left_arm");
        this.tail1 = this.body_parts.getChild("tail1");
        this.tail2 = this.tail1.getChild("tail2");
        this.tail_tip = this.tail2.getChild("tail_tip");
        this.legs = this.Rat.getChild("legs");
        this.right_leg = this.legs.getChild("right_leg");
        this.right_calf = this.right_leg.getChild("right_calf");
        this.right_foot = this.right_calf.getChild("right_foot");
        this.left_leg = this.legs.getChild("left_leg");
        this.left_calf = this.left_leg.getChild("left_calf");
        this.left_foot = this.left_calf.getChild("left_foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Rat = partdefinition.addOrReplaceChild("Rat", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 3.0F));

        PartDefinition body_parts = Rat.addOrReplaceChild("body_parts", CubeListBuilder.create().texOffs(36, 39).addBox(-2.5F, -4.0F, -3.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 39).addBox(-2.5F, -4.0F, -7.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = body_parts.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -4.0F, -2.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(46, 4).addBox(-2.0F, -4.0F, -6.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 29).addBox(-2.0F, -5.0F, -6.5F, 4.0F, 1.0F, 9.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -3.0F, -5.5F));

        PartDefinition epiglotis = neck.addOrReplaceChild("epiglotis", CubeListBuilder.create().texOffs(52, 17).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -6.0F, 1.0F));

        PartDefinition muscles = neck.addOrReplaceChild("muscles", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, -1.0F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -5.0F, 1.0F));

        PartDefinition upper_jaw = neck.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -4.0F, -9.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(26, 19).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 1.0F, 9.0F, new CubeDeformation(-0.01F))
                .texOffs(36, 17).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 2.5F));

        PartDefinition feathers_r1 = upper_jaw.addOrReplaceChild("feathers_r1", CubeListBuilder.create().texOffs(46, 0).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition eyes = upper_jaw.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 42).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -2.0F, -3.0F));

        PartDefinition pupils = eyes.addOrReplaceChild("pupils", CubeListBuilder.create().texOffs(52, 30).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition arms = body_parts.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));

        PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 47).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 48).addBox(-1.0F, 3.0F, -6.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 47).mirror().addBox(1.0F, 0.0F, -4.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 48).mirror().addBox(1.0F, 3.0F, -6.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 0.0F, 0.0F));

        PartDefinition tail1 = body_parts.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(36, 10).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 1.0F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 10).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

        PartDefinition tail_tip = tail2.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0.0F, 1.0F, 13.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition legs = Rat.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(52, 24).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition right_calf = right_leg.addOrReplaceChild("right_calf", CubeListBuilder.create().texOffs(18, 37).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.5F));

        PartDefinition right_foot = right_calf.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(48, 47).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 32).addBox(0.5F, -1.0F, -4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(52, 24).mirror().addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition left_calf = left_leg.addOrReplaceChild("left_calf", CubeListBuilder.create().texOffs(18, 37).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, 1.5F));

        PartDefinition left_foot = left_calf.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(48, 47).mirror().addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 32).mirror().addBox(-1.5F, -1.0F, -4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        TangofteroEntity tangoftero = (TangofteroEntity) entity;

        if (tangoftero.isPreparingSleep()) {
            this.animate(tangoftero.preparingSleepState, ModAnimationDefinitions.TangofteroAnimation.preparing_to_sleep, ageInTicks, 1f);
        } else if (tangoftero.isSleeping()) {
            this.animate(tangoftero.sleepState, ModAnimationDefinitions.TangofteroAnimation.sleep, ageInTicks, 1f);
        } else if (tangoftero.isAwakeing()) {
            this.animate(tangoftero.awakeningState, ModAnimationDefinitions.TangofteroAnimation.awakening, ageInTicks, 1f);
        } else if (tangoftero.isAttacking()) {
            this.animate(tangoftero.attackAnimationState, ModAnimationDefinitions.TangofteroAnimation.bite, ageInTicks, 1f);
        } else {
            this.animate(tangoftero.idleAnimationState, ModAnimationDefinitions.TangofteroAnimation.idle, ageInTicks, 1f);
            this.animate(tangoftero.biteAnimationState, ModAnimationDefinitions.TangofteroAnimation.bite, ageInTicks, 1f);
            this.animate(tangoftero.roarAnimationState, ModAnimationDefinitions.TangofteroAnimation.roar, ageInTicks, 1f);
            this.animateWalk(ModAnimationDefinitions.TangofteroAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        }

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Rat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.neck.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.neck.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return Rat;
    }

}