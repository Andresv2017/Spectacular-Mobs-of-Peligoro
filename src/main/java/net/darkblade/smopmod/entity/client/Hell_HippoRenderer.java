package net.darkblade.smopmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class Hell_HippoRenderer extends MobRenderer<Hell_HippoEntity, Hell_HippoModel<Hell_HippoEntity>> {

    public Hell_HippoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Hell_HippoModel<>(pContext.bakeLayer(ModModelLayers.HELL_HIPPO_LAYER)),2f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hell_HippoEntity entity) {
        String base = entity.isMale() ? "male" : "female";

        if (entity.isSeaweed() && !entity.isSaddled()) {
            return new ResourceLocation(SMOP.MOD_ID, "textures/entity/" + base + "_hell_hippo_seaweed.png");
        }

        String texture = base + "_hell_hippo";

        if (entity.isSaddled()) {
            texture += "_saddle";

            if (entity.hasArmor()) {
                texture += "_armored";
            }

            if (entity.hasChest()) {
                texture += "_chest";
            }
        } else if (entity.hasArmor()) {
            texture += "_armored";
        }
        return new ResourceLocation(SMOP.MOD_ID, "textures/entity/" + texture + ".png");
    }

    //

    @Override
    public void render(Hell_HippoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()){
            pMatrixStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public Hell_HippoModel<Hell_HippoEntity> getModel() {
        return super.getModel();
    }

    @Override
    protected @Nullable RenderType getRenderType(Hell_HippoEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return super.getRenderType(pLivingEntity, pBodyVisible, pTranslucent, pGlowing);
    }
}
