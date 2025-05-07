package net.darkblade.smopmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class Hell_HippoRenderer extends MobRenderer<Hell_HippoEntity, Hell_HippoModel<Hell_HippoEntity>> {

    public Hell_HippoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.HELL_HIPPO_LAYER)),2f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hell_HippoEntity entity) {
        String base = entity.isMale() ? "male" : "female";

        // Seaweed solo aplica si no tiene saddle
        if (entity.isSeaweed() && !entity.isSaddled()) {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/" + base + "_hell_hippo_seaweed.png");
        }

        String suffix = "_hell_hippo";
        if (entity.isSaddled()) suffix += "_saddle";
        if (entity.hasChest() && entity.isSaddled()) suffix += "_chest";

        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/" + base + suffix + ".png");
    }


    @Override
    public void render(Hell_HippoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()){
            pMatrixStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public Vec3 getRenderOffset(Hell_HippoEntity entity, float partialTicks) {
        if (entity.isSleeping()) {
            return new Vec3(0.0D, -1.1D, 0.0D); // 🔽 ajusta este valor si aún queda flotando
        }
        return super.getRenderOffset(entity, partialTicks);
    }

}
