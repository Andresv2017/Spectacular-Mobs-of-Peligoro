package net.darkblade.smopmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TangofteroRender extends MobRenderer<TangofteroEntity, TangofteroModel<TangofteroEntity>> {

    public TangofteroRender(EntityRendererProvider.Context pContext) {
        super(pContext, new TangofteroModel<TangofteroEntity>(pContext.bakeLayer(ModModelLayers.TANGOFTERO_LAYER)),1f);
    }

    @Override
    public ResourceLocation getTextureLocation(TangofteroEntity tangofteroEntity) {
        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/tangoftero_lavander.png");
    }
    @Override
    public void render(TangofteroEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
