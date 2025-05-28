package net.darkblade.smopmod.entity.client.salmon;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SalmonRenderer extends MobRenderer<SalmonEntity, SalmonModel<SalmonEntity>> {

    public SalmonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SalmonModel<SalmonEntity>(pContext.bakeLayer(ModModelLayers.SALMON)),.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(SalmonEntity salmonEntity) {
        if (salmonEntity.isMale()) {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/salmon/salmon_male.png");
        } else {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/salmon/salmon_female.png");
        }
    }

    @Override
    public void render(SalmonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
