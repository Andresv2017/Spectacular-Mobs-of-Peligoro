package net.darkblade.smopmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.TangofteroVariant;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class KriftognathusRender extends MobRenderer<KriftognathusEntity, KriftognathusModel<KriftognathusEntity>> {

    public KriftognathusRender(EntityRendererProvider.Context pContext) {
        super(pContext, new KriftognathusModel<KriftognathusEntity>(pContext.bakeLayer(ModModelLayers.KRIFTO)),.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(KriftognathusEntity tangofteroEntity) {
        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_male.png");
    }
    @Override
    public void render(KriftognathusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
