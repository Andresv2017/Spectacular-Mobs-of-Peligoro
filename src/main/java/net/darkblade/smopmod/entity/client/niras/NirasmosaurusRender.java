package net.darkblade.smopmod.entity.client.niras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.client.krifto.KriftognathusModel;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.darkblade.smopmod.entity.custom.NirasmosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NirasmosaurusRender extends MobRenderer<NirasmosaurusEntity, NirasmosaurusModel<NirasmosaurusEntity>> {

    public NirasmosaurusRender(EntityRendererProvider.Context pContext) {
        super(pContext, new NirasmosaurusModel<NirasmosaurusEntity>(pContext.bakeLayer(ModModelLayers.NIRAS)),.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(NirasmosaurusEntity nirasEntity) {
        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/niras/niras_male.png");
    }
    @Override
    public void render(NirasmosaurusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
