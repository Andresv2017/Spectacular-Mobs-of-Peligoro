package net.darkblade.smopmod.entity.client.salmon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SalmonModel<T extends SalmonEntity> extends HierarchicalModel<T> {

    private final ModelPart MVNF;
    private final ModelPart body_parts;
    private final ModelPart head;
    private final ModelPart pupils;
    private final ModelPart upper_whiskers;
    private final ModelPart upper_right_whisker;
    private final ModelPart upper_left_whisker;
    private final ModelPart lower_whiskers;
    private final ModelPart lower_right_whisker;
    private final ModelPart lower_left_whisker;
    private final ModelPart snout;
    private final ModelPart lower_jaw;
    private final ModelPart lower_whisker;
    private final ModelPart front_fins;
    private final ModelPart right_front_fin;
    private final ModelPart left_front_fin;
    private final ModelPart back_fins;
    private final ModelPart right_back_fin;
    private final ModelPart left_back_fin;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart fin;

    public SalmonModel(ModelPart root) {
        this.MVNF = root.getChild("MVNF");
        this.body_parts = this.MVNF.getChild("body_parts");
        this.head = this.body_parts.getChild("head");
        this.pupils = this.head.getChild("pupils");
        this.upper_whiskers = this.head.getChild("upper_whiskers");
        this.upper_right_whisker = this.upper_whiskers.getChild("upper_right_whisker");
        this.upper_left_whisker = this.upper_whiskers.getChild("upper_left_whisker");
        this.lower_whiskers = this.head.getChild("lower_whiskers");
        this.lower_right_whisker = this.lower_whiskers.getChild("lower_right_whisker");
        this.lower_left_whisker = this.lower_whiskers.getChild("lower_left_whisker");
        this.snout = this.head.getChild("snout");
        this.lower_jaw = this.head.getChild("lower_jaw");
        this.lower_whisker = this.lower_jaw.getChild("lower_whisker");
        this.front_fins = this.body_parts.getChild("front_fins");
        this.right_front_fin = this.front_fins.getChild("right_front_fin");
        this.left_front_fin = this.front_fins.getChild("left_front_fin");
        this.back_fins = this.body_parts.getChild("back_fins");
        this.right_back_fin = this.back_fins.getChild("right_back_fin");
        this.left_back_fin = this.back_fins.getChild("left_back_fin");
        this.tail1 = this.body_parts.getChild("tail1");
        this.tail2 = this.tail1.getChild("tail2");
        this.fin = this.tail2.getChild("fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition MVNF = partdefinition.addOrReplaceChild("MVNF", CubeListBuilder.create(), PartPose.offset(0.0F, 18.96F, 2.88F));

        PartDefinition body_parts = MVNF.addOrReplaceChild("body_parts", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -9.46F, -9.88F, 6.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(0.0F, -14.46F, -0.88F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body_parts.addOrReplaceChild("head", CubeListBuilder.create().texOffs(60, 46).addBox(-2.0F, -6.68F, -2.8F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(34, 46).addBox(-2.0F, -6.68F, -11.8F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-2.0F, -0.68F, -9.8F, 4.0F, 3.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.72F, -10.08F));

        PartDefinition pupils = head.addOrReplaceChild("pupils", CubeListBuilder.create().texOffs(33, 53).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -2.18F, -4.3F));

        PartDefinition upper_whiskers = head.addOrReplaceChild("upper_whiskers", CubeListBuilder.create(), PartPose.offset(0.0F, -3.6F, -9.0F));

        PartDefinition upper_right_whisker = upper_whiskers.addOrReplaceChild("upper_right_whisker", CubeListBuilder.create(), PartPose.offset(-2.16F, 0.0F, 0.0F));

        PartDefinition upper_right_whisker_r1 = upper_right_whisker.addOrReplaceChild("upper_right_whisker_r1", CubeListBuilder.create().texOffs(64, 63).addBox(0.0F, -6.0F, -2.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.16F, -0.08F, -0.3F, 0.1745F, 0.0F, -0.1745F));

        PartDefinition upper_left_whisker = upper_whiskers.addOrReplaceChild("upper_left_whisker", CubeListBuilder.create(), PartPose.offset(2.16F, 0.0F, 0.0F));

        PartDefinition upper_left_whisker_r1 = upper_left_whisker.addOrReplaceChild("upper_left_whisker_r1", CubeListBuilder.create().texOffs(64, 63).mirror().addBox(0.0F, -6.0F, -2.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.16F, -0.08F, -0.3F, 0.1745F, 0.0F, 0.1745F));

        PartDefinition lower_whiskers = head.addOrReplaceChild("lower_whiskers", CubeListBuilder.create(), PartPose.offset(0.0F, -1.44F, -8.28F));

        PartDefinition lower_right_whisker = lower_whiskers.addOrReplaceChild("lower_right_whisker", CubeListBuilder.create(), PartPose.offset(-2.16F, 0.0F, 0.0F));

        PartDefinition lower_right_whisker_r1 = lower_right_whisker.addOrReplaceChild("lower_right_whisker_r1", CubeListBuilder.create().texOffs(58, 63).addBox(0.0F, 0.01F, -2.52F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.16F, -0.25F, 0.0F, 0.1745F, 0.0F, 0.1745F));

        PartDefinition lower_left_whisker = lower_whiskers.addOrReplaceChild("lower_left_whisker", CubeListBuilder.create(), PartPose.offset(2.16F, 0.0F, 0.0F));

        PartDefinition lower_left_whisker_r1 = lower_left_whisker.addOrReplaceChild("lower_left_whisker_r1", CubeListBuilder.create().texOffs(58, 63).mirror().addBox(0.0F, 0.01F, -2.52F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.16F, -0.25F, 0.0F, 0.1745F, 0.0F, -0.1745F));

        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(32, 61).addBox(-2.0F, -5.96F, -1.84F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 69).addBox(-2.0F, 0.04F, -2.84F, 4.0F, 9.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -0.72F, -12.96F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(58, 16).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(-0.01F))
                .texOffs(36, 16).addBox(-2.0F, 0.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.32F, -2.8F));

        PartDefinition lip_r1 = lower_jaw.addOrReplaceChild("lip_r1", CubeListBuilder.create().texOffs(36, 25).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 0.6545F, 0.0F, 0.0F));

        PartDefinition lower_whisker = lower_jaw.addOrReplaceChild("lower_whisker", CubeListBuilder.create().texOffs(16, 64).addBox(0.0F, 0.0F, -1.96F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -4.04F));

        PartDefinition front_fins = body_parts.addOrReplaceChild("front_fins", CubeListBuilder.create(), PartPose.offset(0.0F, 5.04F, -8.88F));

        PartDefinition right_front_fin = front_fins.addOrReplaceChild("right_front_fin", CubeListBuilder.create(), PartPose.offset(-3.0F, 0.0F, 0.0F));

        PartDefinition fin_r1 = right_front_fin.addOrReplaceChild("fin_r1", CubeListBuilder.create().texOffs(62, 24).addBox(0.0F, -2.0F, 0.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition left_front_fin = front_fins.addOrReplaceChild("left_front_fin", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition fin_r2 = left_front_fin.addOrReplaceChild("fin_r2", CubeListBuilder.create().texOffs(62, 24).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition back_fins = body_parts.addOrReplaceChild("back_fins", CubeListBuilder.create(), PartPose.offset(0.0F, 4.54F, -1.38F));

        PartDefinition right_back_fin = back_fins.addOrReplaceChild("right_back_fin", CubeListBuilder.create().texOffs(63, 39).addBox(0.2288F, 0.0289F, -1.0018F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 1.0F));

        PartDefinition left_back_fin = back_fins.addOrReplaceChild("left_back_fin", CubeListBuilder.create().texOffs(63, 39).mirror().addBox(-0.2288F, 0.0289F, -1.0018F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 1.0F, 1.0F));

        PartDefinition tail1 = body_parts.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(34, 27).addBox(-2.5F, -5.0F, -1.5F, 5.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.46F, 2.62F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(26, 65).addBox(0.0F, -4.5F, 1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-2.0F, -2.5F, -2.5F, 4.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(46, 61).addBox(0.0F, 2.5F, -3.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 8.0F));

        PartDefinition fin = tail2.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(18, 49).addBox(0.0F, -5.5F, 0.0F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(SalmonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        MVNF.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return MVNF;
    }
}
