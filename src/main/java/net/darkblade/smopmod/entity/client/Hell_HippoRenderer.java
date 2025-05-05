package net.darkblade.smopmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Hell_HippoRenderer extends MobRenderer<Hell_HippoEntity, Hell_HippoModel<Hell_HippoEntity>> {

    public Hell_HippoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.HELL_HIPPO_LAYER)),2f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hell_HippoEntity entity) {
        String base = entity.isMale() ? "male" : "female";
        String saddle = entity.isSaddled() ? "_saddle" : "";
        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/" + base + "_hell_hippo" + saddle + ".png");
    }


    @Override
    public void render(Hell_HippoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()){
            pMatrixStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
