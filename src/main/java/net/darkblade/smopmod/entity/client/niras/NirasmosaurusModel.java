package net.darkblade.smopmod.entity.client.niras;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkblade.smopmod.entity.client.niras.animations.NirasmosaurusLandAnimations;
import net.darkblade.smopmod.entity.client.niras.animations.NirasmosaurusWaterAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class NirasmosaurusModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart gNirasmo;
    private final ModelPart gBodyparts;
    private final ModelPart gChest_n_corals;
    private final ModelPart gCorals;
    private final ModelPart gCoral1;
    private final ModelPart gCoral2;
    private final ModelPart gCoral3;
    private final ModelPart gCoral4;
    private final ModelPart gCoral5;
    private final ModelPart gChest;
    private final ModelPart gNeck;
    private final ModelPart gTroath;
    private final ModelPart gHead;
    private final ModelPart gUpperjaw;
    private final ModelPart GEyes;
    private final ModelPart gLowerjaw;
    private final ModelPart gRight_saliva;
    private final ModelPart gLeft_saliva;
    private final ModelPart gChin;
    private final ModelPart gTongue;
    private final ModelPart gTail;
    private final ModelPart gFin;
    private final ModelPart gFlippers;
    private final ModelPart gFrontflippers;
    private final ModelPart gRightfrontflipper;
    private final ModelPart gLeftfrontflipper;
    private final ModelPart gBackflippers;
    private final ModelPart gRightbackflipper;
    private final ModelPart gLeftbackflipper;

    public NirasmosaurusModel(ModelPart root) {
        this.gNirasmo = root.getChild("gNirasmo");
        this.gBodyparts = this.gNirasmo.getChild("gBodyparts");
        this.gChest_n_corals = this.gBodyparts.getChild("gChest_n_corals");
        this.gCorals = this.gChest_n_corals.getChild("gCorals");
        this.gCoral1 = this.gCorals.getChild("gCoral1");
        this.gCoral2 = this.gCorals.getChild("gCoral2");
        this.gCoral3 = this.gCorals.getChild("gCoral3");
        this.gCoral4 = this.gCorals.getChild("gCoral4");
        this.gCoral5 = this.gCorals.getChild("gCoral5");
        this.gChest = this.gBodyparts.getChild("gChest");
        this.gNeck = this.gBodyparts.getChild("gNeck");
        this.gTroath = this.gNeck.getChild("gTroath");
        this.gHead = this.gNeck.getChild("gHead");
        this.gUpperjaw = this.gHead.getChild("gUpperjaw");
        this.GEyes = this.gUpperjaw.getChild("GEyes");
        this.gLowerjaw = this.gHead.getChild("gLowerjaw");
        this.gRight_saliva = this.gLowerjaw.getChild("gRight_saliva");
        this.gLeft_saliva = this.gLowerjaw.getChild("gLeft_saliva");
        this.gChin = this.gLowerjaw.getChild("gChin");
        this.gTongue = this.gLowerjaw.getChild("gTongue");
        this.gTail = this.gBodyparts.getChild("gTail");
        this.gFin = this.gTail.getChild("gFin");
        this.gFlippers = this.gNirasmo.getChild("gFlippers");
        this.gFrontflippers = this.gFlippers.getChild("gFrontflippers");
        this.gRightfrontflipper = this.gFrontflippers.getChild("gRightfrontflipper");
        this.gLeftfrontflipper = this.gFrontflippers.getChild("gLeftfrontflipper");
        this.gBackflippers = this.gFlippers.getChild("gBackflippers");
        this.gRightbackflipper = this.gBackflippers.getChild("gRightbackflipper");
        this.gLeftbackflipper = this.gBackflippers.getChild("gLeftbackflipper");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition gNirasmo = partdefinition.addOrReplaceChild("gNirasmo", CubeListBuilder.create(), PartPose.offset(0.0F, 20.5F, 15.0F));

        PartDefinition gBodyparts = gNirasmo.addOrReplaceChild("gBodyparts", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition gChest_n_corals = gBodyparts.addOrReplaceChild("gChest_n_corals", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -4.5F));

        PartDefinition gCorals = gChest_n_corals.addOrReplaceChild("gCorals", CubeListBuilder.create(), PartPose.offset(0.0385F, -16.5F, 0.7021F));

        PartDefinition gCoral1 = gCorals.addOrReplaceChild("gCoral1", CubeListBuilder.create(), PartPose.offset(4.9615F, 0.0F, -9.7021F));

        PartDefinition coral_r1 = gCoral1.addOrReplaceChild("coral_r1", CubeListBuilder.create().texOffs(0, -1).addBox(0.0F, -14.0F, -7.5F, 0.0F, 14.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition gCoral2 = gCorals.addOrReplaceChild("gCoral2", CubeListBuilder.create(), PartPose.offset(-0.0385F, 0.0F, -1.7021F));

        PartDefinition coral_r2 = gCoral2.addOrReplaceChild("coral_r2", CubeListBuilder.create().texOffs(34, 50).addBox(0.0F, -16.0F, -7.5F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition gCoral3 = gCorals.addOrReplaceChild("gCoral3", CubeListBuilder.create(), PartPose.offset(-4.0385F, 0.0F, -5.7021F));

        PartDefinition coral_r3 = gCoral3.addOrReplaceChild("coral_r3", CubeListBuilder.create().texOffs(0, -14).addBox(0.0F, -14.0F, -6.5F, 0.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition gCoral4 = gCorals.addOrReplaceChild("gCoral4", CubeListBuilder.create(), PartPose.offset(3.9615F, 0.0F, 7.2979F));

        PartDefinition coral_r4 = gCoral4.addOrReplaceChild("coral_r4", CubeListBuilder.create().texOffs(73, -16).addBox(0.0F, -16.0F, -8.5F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition gCoral5 = gCorals.addOrReplaceChild("gCoral5", CubeListBuilder.create(), PartPose.offset(-5.0385F, 0.0F, 9.2979F));

        PartDefinition coral_r5 = gCoral5.addOrReplaceChild("coral_r5", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -12.0F, -7.5F, 0.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition gChest = gBodyparts.addOrReplaceChild("gChest", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.5F, -20.5F, 16.0F, 25.0F, 41.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.5F));

        PartDefinition gNeck = gBodyparts.addOrReplaceChild("gNeck", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -25.0F));

        PartDefinition gTroath = gNeck.addOrReplaceChild("gTroath", CubeListBuilder.create().texOffs(0, 66).addBox(-3.5F, -8.5F, -10.0F, 7.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

        PartDefinition gHead = gNeck.addOrReplaceChild("gHead", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, -14.0F));

        PartDefinition gUpperjaw = gHead.addOrReplaceChild("gUpperjaw", CubeListBuilder.create().texOffs(108, 96).addBox(-4.5F, -8.0F, -19.0F, 9.0F, 8.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(32, 129).addBox(-4.5F, -8.0F, -28.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(138, 138).addBox(-4.5F, 2.0F, -28.0F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition GEyes = gUpperjaw.addOrReplaceChild("GEyes", CubeListBuilder.create().texOffs(114, 91).addBox(-4.5F, -0.5F, -1.5F, 9.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -3.5F, -9.5F));

        PartDefinition gLowerjaw = gHead.addOrReplaceChild("gLowerjaw", CubeListBuilder.create().texOffs(138, 123).addBox(-4.5F, 2.0F, -28.0F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(114, 0).addBox(-4.5F, 0.0F, -19.0F, 9.0F, 8.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(66, 163).addBox(-4.0F, -5.0F, -12.0F, 8.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(140, 62).addBox(-4.5F, -2.0F, -28.0F, 9.0F, 4.0F, 9.0F, new CubeDeformation(-0.01F))
                .texOffs(116, 3).addBox(-1.0F, -1.0F, -20.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition gRight_saliva = gLowerjaw.addOrReplaceChild("gRight_saliva", CubeListBuilder.create().texOffs(147, 24).mirror().addBox(0.0F, -4.0F, -9.5F, 0.0F, 8.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.25F, -4.0F, -9.5F));

        PartDefinition gLeft_saliva = gLowerjaw.addOrReplaceChild("gLeft_saliva", CubeListBuilder.create().texOffs(147, 16).addBox(0.0F, -4.0F, -9.5F, 0.0F, 8.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(4.25F, -4.0F, -9.5F));

        PartDefinition gChin = gLowerjaw.addOrReplaceChild("gChin", CubeListBuilder.create().texOffs(54, 66).addBox(-2.5F, -1.0F, -12.5F, 5.0F, 5.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -9.5F));

        PartDefinition gTongue = gLowerjaw.addOrReplaceChild("gTongue", CubeListBuilder.create().texOffs(117, 5).addBox(0.0F, -1.5F, -6.5F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -19.5F));

        PartDefinition gTail = gBodyparts.addOrReplaceChild("gTail", CubeListBuilder.create().texOffs(54, 96).addBox(-3.0F, -6.0F, 0.0F, 6.0F, 12.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 16.0F));

        PartDefinition gFin = gTail.addOrReplaceChild("gFin", CubeListBuilder.create().texOffs(108, 123).addBox(0.0F, -10.0F, -0.5F, 0.0F, 20.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 16.5F));

        PartDefinition gFlippers = gNirasmo.addOrReplaceChild("gFlippers", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition gFrontflippers = gFlippers.addOrReplaceChild("gFrontflippers", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, -13.0F));

        PartDefinition gRightfrontflipper = gFrontflippers.addOrReplaceChild("gRightfrontflipper", CubeListBuilder.create().texOffs(114, 27).mirror().addBox(-1.5F, 0.0F, -6.0F, 3.0F, 23.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 103).mirror().addBox(0.5F, 13.0F, -5.0F, 0.0F, 19.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.5F, 0.0F, 0.0F));

        PartDefinition gLeftfrontflipper = gFrontflippers.addOrReplaceChild("gLeftfrontflipper", CubeListBuilder.create().texOffs(114, 27).addBox(-1.5F, 0.0F, -6.0F, 3.0F, 23.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 103).addBox(-0.5F, 13.0F, -5.0F, 0.0F, 19.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, 0.0F, 0.0F));

        PartDefinition gBackflippers = gFlippers.addOrReplaceChild("gBackflippers", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 8.0F));

        PartDefinition gRightbackflipper = gBackflippers.addOrReplaceChild("gRightbackflipper", CubeListBuilder.create().texOffs(114, 62).mirror().addBox(-1.5F, 0.0F, -5.0F, 3.0F, 19.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 138).mirror().addBox(0.5F, 8.0F, -2.0F, 0.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.5F, 0.0F, 0.0F));

        PartDefinition gLeftbackflipper = gBackflippers.addOrReplaceChild("gLeftbackflipper", CubeListBuilder.create().texOffs(114, 62).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 19.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 138).addBox(-0.5F, 8.0F, -2.0F, 0.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(NirasmosaurusLandAnimations.walking, limbSwing, limbSwingAmount, 1f, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        gNirasmo.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {return gNirasmo;}
}
