package net.darkblade.smopmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class Baby_Hell_HippoModel<T extends Entity> extends HierarchicalModel<T> {


    private final ModelPart Hipopotamo_Infernal;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart troath;
    private final ModelPart head;
    private final ModelPart eyes;
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
    private final ModelPart left_leg2;

	public Baby_Hell_HippoModel(ModelPart root) {
        this.Hipopotamo_Infernal = root.getChild("Hipopotamo_Infernal");
        this.body = this.Hipopotamo_Infernal.getChild("body");
        this.neck = this.body.getChild("neck");
        this.troath = this.neck.getChild("troath");
        this.head = this.neck.getChild("head");
        this.eyes = this.head.getChild("eyes");
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
        this.left_leg2 = this.back_legs.getChild("left_leg2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Hipopotamo_Infernal = partdefinition.addOrReplaceChild("Hipopotamo_Infernal", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 9.5F));

        PartDefinition body = Hipopotamo_Infernal.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -3.0F, -10.5F, 12.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(66, 72).addBox(0.0F, -6.0F, -5.5F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -18.5F));

        PartDefinition troath = neck.addOrReplaceChild("troath", CubeListBuilder.create().texOffs(44, 65).addBox(1.0F, -8.0F, -9.0F, 0.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(50, 28).addBox(-3.0F, -6.0F, -9.0F, 8.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 48).addBox(-5.0F, -5.0F, -7.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.01F))
                .texOffs(44, 52).addBox(-1.0F, -5.0F, -14.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 59).addBox(-8.0F, -1.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 59).mirror().addBox(3.0F, -1.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(58, 0).addBox(-5.0F, -3.0F, -16.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(66, 65).addBox(-4.0F, -3.0F, -10.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(40, 78).addBox(-4.0F, 1.0F, -15.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -9.0F));

        PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 75).addBox(-5.0F, -0.5F, -1.5F, 8.0F, 1.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, -2.5F, -5.5F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(82, 48).addBox(-4.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -3.0F, -15.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(-1.0F, -3.5F, -1.0F));

        PartDefinition right_ear = ears.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(82, 52).addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

        PartDefinition left_ear = ears.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(82, 52).mirror().addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, 0.0F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(58, 10).addBox(-5.0F, 0.0F, -4.0F, 6.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(58, 20).addBox(-6.0F, 1.0F, -9.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 79).addBox(-5.0F, -1.0F, -8.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.0F, 0.0F, -7.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -6.5F, -7.5F, 14.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -13.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(82, 72).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 2.5F));

        PartDefinition tailend = tail.addOrReplaceChild("tailend", CubeListBuilder.create().texOffs(24, 73).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 1.0F));

        PartDefinition legs = Hipopotamo_Infernal.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_legs = legs.addOrReplaceChild("front_legs", CubeListBuilder.create(), PartPose.offset(-6.5F, 0.0F, -16.0F));

        PartDefinition right_leg1 = front_legs.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(24, 52).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg1 = front_legs.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(24, 52).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(13.0F, 0.0F, 0.0F));

        PartDefinition back_legs = legs.addOrReplaceChild("back_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg2 = back_legs.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(0, 52).addBox(-2.5F, 0.0F, -3.5F, 5.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.0F, 0.0F));

        PartDefinition left_leg2 = back_legs.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-2.5F, 0.0F, -3.5F, 5.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.5F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Hipopotamo_Infernal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return null;
    }
}