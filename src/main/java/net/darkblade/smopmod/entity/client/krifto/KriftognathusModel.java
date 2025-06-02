package net.darkblade.smopmod.entity.client.krifto;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkblade.smopmod.entity.animations.ModAnimationDefinitions;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class KriftognathusModel<T extends KriftognathusEntity> extends HierarchicalModel<T> {

    private final ModelPart gPiglug;
    private final ModelPart gBody_parts;
    private final ModelPart gNeck;
    private final ModelPart gHGead;
    private final ModelPart gEyes;
    private final ModelPart gRight_pupil;
    private final ModelPart gLeft_pupil;
    private final ModelPart gLower_jaw;
    private final ModelPart gGoiter;
    private final ModelPart gTail;
    private final ModelPart gLegs;
    private final ModelPart gFront_legs;
    private final ModelPart gRight_leg1;
    private final ModelPart gRight_claws1;
    private final ModelPart gRight_wing;
    private final ModelPart gLeft_leg1;
    private final ModelPart gLeft_claws1;
    private final ModelPart gLeft_wing;
    private final ModelPart gBack_legs;
    private final ModelPart gRight_leg2;
    private final ModelPart gRight_calf;
    private final ModelPart gRight_claws2;
    private final ModelPart gLeft_leg2;
    private final ModelPart gLeft_calf;
    private final ModelPart gLeft_claws2;

    public KriftognathusModel(ModelPart root) {
        this.gPiglug = root.getChild("gPiglug");
        this.gBody_parts = this.gPiglug.getChild("gBody_parts");
        this.gNeck = this.gBody_parts.getChild("gNeck");
        this.gHGead = this.gNeck.getChild("gHGead");
        this.gEyes = this.gHGead.getChild("gEyes");
        this.gRight_pupil = this.gEyes.getChild("gRight_pupil");
        this.gLeft_pupil = this.gEyes.getChild("gLeft_pupil");
        this.gLower_jaw = this.gHGead.getChild("gLower_jaw");
        this.gGoiter = this.gLower_jaw.getChild("gGoiter");
        this.gTail = this.gBody_parts.getChild("gTail");
        this.gLegs = this.gPiglug.getChild("gLegs");
        this.gFront_legs = this.gLegs.getChild("gFront_legs");
        this.gRight_leg1 = this.gFront_legs.getChild("gRight_leg1");
        this.gRight_claws1 = this.gRight_leg1.getChild("gRight_claws1");
        this.gRight_wing = this.gRight_leg1.getChild("gRight_wing");
        this.gLeft_leg1 = this.gFront_legs.getChild("gLeft_leg1");
        this.gLeft_claws1 = this.gLeft_leg1.getChild("gLeft_claws1");
        this.gLeft_wing = this.gLeft_leg1.getChild("gLeft_wing");
        this.gBack_legs = this.gLegs.getChild("gBack_legs");
        this.gRight_leg2 = this.gBack_legs.getChild("gRight_leg2");
        this.gRight_calf = this.gRight_leg2.getChild("gRight_calf");
        this.gRight_claws2 = this.gRight_calf.getChild("gRight_claws2");
        this.gLeft_leg2 = this.gBack_legs.getChild("gLeft_leg2");
        this.gLeft_calf = this.gLeft_leg2.getChild("gLeft_calf");
        this.gLeft_claws2 = this.gLeft_calf.getChild("gLeft_claws2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition gPiglug = partdefinition.addOrReplaceChild("gPiglug", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0086F, 4.1305F));

        PartDefinition gBody_parts = gPiglug.addOrReplaceChild("gBody_parts", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body_r1 = gBody_parts.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-2.5F, -7.0F, -5.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -4.0F, -7.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition gNeck = gBody_parts.addOrReplaceChild("gNeck", CubeListBuilder.create().texOffs(14, 30).addBox(-1.5F, -9.0F, -2.5F, 3.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 28).addBox(0.0F, -4.0F, -4.5F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 20).addBox(-2.5F, -6.0F, -3.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -1.0086F, -6.6305F));

        PartDefinition gHGead = gNeck.addOrReplaceChild("gHGead", CubeListBuilder.create().texOffs(38, 44).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(36, 20).addBox(-1.5F, -2.0F, -10.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 37).addBox(-1.5F, 1.0F, -10.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.01F))
                .texOffs(26, 44).addBox(-2.0F, -6.0F, -5.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 38).addBox(0.0F, -5.0F, -4.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(26, 44).mirror().addBox(1.0F, -6.0F, -5.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.5F, -2.5F));

        PartDefinition head_r1 = gHGead.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(56, 34).mirror().addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition head_r2 = gHGead.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(56, 34).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition gEyes = gHGead.addOrReplaceChild("gEyes", CubeListBuilder.create().texOffs(26, 8).addBox(-2.0F, -1.0F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -1.0F, -3.5F));

        PartDefinition gRight_pupil = gEyes.addOrReplaceChild("gRight_pupil", CubeListBuilder.create().texOffs(36, 28).addBox(0.025F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.02F)), PartPose.offset(-2.025F, -0.5F, -1.0F));

        PartDefinition gLeft_pupil = gEyes.addOrReplaceChild("gLeft_pupil", CubeListBuilder.create().texOffs(36, 28).mirror().addBox(-1.025F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(2.025F, -0.5F, -1.0F));

        PartDefinition gLower_jaw = gHGead.addOrReplaceChild("gLower_jaw", CubeListBuilder.create().texOffs(30, 30).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-1.5F, 1.0F, -10.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(42, 8).addBox(-1.5F, -1.0F, -10.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition gGoiter = gLower_jaw.addOrReplaceChild("gGoiter", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 2.0F, -2.0F));

        PartDefinition gTail = gBody_parts.addOrReplaceChild("gTail", CubeListBuilder.create(), PartPose.offset(0.0F, -3.8353F, 1.5135F));

        PartDefinition tail_r1 = gTail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition gLegs = gPiglug.addOrReplaceChild("gLegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition gFront_legs = gLegs.addOrReplaceChild("gFront_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0086F, -4.6305F));

        PartDefinition gRight_leg1 = gFront_legs.addOrReplaceChild("gRight_leg1", CubeListBuilder.create().texOffs(16, 44).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 50).addBox(-1.0F, 0.0F, -3.5F, 0.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 35).addBox(-1.0F, 1.0F, 1.5F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 0.0F, 0.0F));

        PartDefinition gRight_claws1 = gRight_leg1.addOrReplaceChild("gRight_claws1", CubeListBuilder.create().texOffs(42, 15).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 1.0F));

        PartDefinition gRight_wing = gRight_leg1.addOrReplaceChild("gRight_wing", CubeListBuilder.create(), PartPose.offset(-1.0F, 8.0F, 0.0F));

        PartDefinition right_wing_r1 = gRight_wing.addOrReplaceChild("right_wing_r1", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(0.0F, -15.0F, -2.5F, 0.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition gLeft_leg1 = gFront_legs.addOrReplaceChild("gLeft_leg1", CubeListBuilder.create().texOffs(16, 44).mirror().addBox(-1.0F, 0.0F, -1.5F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(10, 50).mirror().addBox(1.0F, 0.0F, -3.5F, 0.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 35).mirror().addBox(1.0F, 1.0F, 1.5F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 0.0F, 0.0F));

        PartDefinition gLeft_claws1 = gLeft_leg1.addOrReplaceChild("gLeft_claws1", CubeListBuilder.create().texOffs(42, 15).mirror().addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 7.0F, 1.0F));

        PartDefinition gLeft_wing = gLeft_leg1.addOrReplaceChild("gLeft_wing", CubeListBuilder.create(), PartPose.offset(1.0F, 8.0F, 0.0F));

        PartDefinition left_wing_r1 = gLeft_wing.addOrReplaceChild("left_wing_r1", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -15.0F, -2.5F, 0.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition gBack_legs = gLegs.addOrReplaceChild("gBack_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5086F, 0.3695F));

        PartDefinition gRight_leg2 = gBack_legs.addOrReplaceChild("gRight_leg2", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition gRight_calf = gRight_leg2.addOrReplaceChild("gRight_calf", CubeListBuilder.create().texOffs(50, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 1.5F));

        PartDefinition gRight_claws2 = gRight_calf.addOrReplaceChild("gRight_claws2", CubeListBuilder.create().texOffs(38, 51).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -0.5F));

        PartDefinition gLeft_leg2 = gBack_legs.addOrReplaceChild("gLeft_leg2", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-1.0F, -0.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition gLeft_calf = gLeft_leg2.addOrReplaceChild("gLeft_calf", CubeListBuilder.create().texOffs(50, 45).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.5F, 1.5F));

        PartDefinition gLeft_claws2 = gLeft_calf.addOrReplaceChild("gLeft_claws2", CubeListBuilder.create().texOffs(38, 51).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 4.0F, -0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(KriftognathusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        // Sleeping Animations
        this.animate(entity.preparingSleepState, KriftoAnimations.sleep_preparing, ageInTicks, 1f);
        this.animate(entity.sleepState, KriftoAnimations.sleep, ageInTicks, 1f);
        this.animate(entity.awakeningState, KriftoAnimations.awakening, ageInTicks, 1f);

        // Basic Animations
        this.animate(entity.getDeathAnimationState(), KriftoAnimations.death, ageInTicks, 1f);
        this.animate(entity.getSprintAnimationState(), KriftoAnimations.sprint, ageInTicks, 1f);
        this.animate(entity.getWalkAnimationState(), KriftoAnimations.walk, ageInTicks, 1f);
        this.animate(entity.getIdleAnimationState(), KriftoAnimations.lidle, ageInTicks, 1f);
        this.animate(entity.getWaterIdleAnimationState(), KriftoAnimations.swim, ageInTicks, 1f);

        // Flying Animations
        this.animate(entity.getFlyIdleAnimationState(), KriftoAnimations.aidle, ageInTicks, 1f);
        this.animate(entity.getFlyMoveAnimationState(), KriftoAnimations.flight, ageInTicks, 1f);

        // Independent Animations
        this.animate(entity.attackAnimationState, KriftoAnimations.attack, ageInTicks, 1f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        gPiglug.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return gPiglug;
    }
}
