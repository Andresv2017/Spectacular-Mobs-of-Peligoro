package net.darkblade.smopmod.entity.client.hell_hippo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Hell_HippoRenderer extends MobRenderer<Hell_HippoEntity, EntityModel<Hell_HippoEntity>> {

    private final EntityModel<Hell_HippoEntity> adultModel;
    private final EntityModel<Hell_HippoEntity> babyModel;

    public Hell_HippoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.HELL_HIPPO_LAYER)), 2f);
        this.adultModel = new Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.HELL_HIPPO_LAYER));
        this.babyModel = new Baby_Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.BABY_HELL_HIPPO_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Hell_HippoEntity entity) {
        if (entity.isBaby()) {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/hell_hippo/baby_hell_hippo.png");
        }

        String base = entity.isMale() ? "male" : "female";

        if (entity.isSeaweed() && !entity.isSaddled()) {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/hell_hippo/" + base + "_hell_hippo_seaweed.png");
        }

        String texture = base + "_hell_hippo";

        if (entity.isSaddled()) {
            texture += "_saddle";
            if (entity.hasArmor()) texture += "_armored";
            if (entity.hasChest()) texture += "_chest";
        } else if (entity.hasArmor()) {
            texture += "_armored";
        }

        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/hell_hippo/" + texture + ".png");
    }


    @Override
    public void render(Hell_HippoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // Cambia el modelo dependiendo de si es cría
        this.model = pEntity.isBaby() ? babyModel : adultModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

