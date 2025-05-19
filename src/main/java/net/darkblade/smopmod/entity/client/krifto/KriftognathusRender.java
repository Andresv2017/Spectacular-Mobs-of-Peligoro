package net.darkblade.smopmod.entity.client.krifto;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

import static net.darkblade.smopmod.entity.custom.KriftognathusEntity.SPAWN_BIOME;

public class KriftognathusRender extends MobRenderer<KriftognathusEntity, KriftognathusModel<KriftognathusEntity>> {

    public KriftognathusRender(EntityRendererProvider.Context pContext) {
        super(pContext, new KriftognathusModel<KriftognathusEntity>(pContext.bakeLayer(ModModelLayers.KRIFTO)),.4f);
    }

    private static final ResourceLocation MALE_TEXTURE =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_male.png");
    private static final ResourceLocation FEMALE_TEXTURE =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_female.png");

    private static final ResourceLocation MALE_JUNGLE =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_male_jungle.png");
    private static final ResourceLocation FEMALE_JUNGLE =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_female_jungle.png");

    private static final ResourceLocation MALE_ARID =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_male_arid.png");
    private static final ResourceLocation FEMALE_ARID =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_female_arid.png");

    private static final ResourceLocation MALE_FROSTY =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_male_frosty.png");
    private static final ResourceLocation FEMALE_FROSTY =
            new ResourceLocation(SMOP.MOD_ID, "textures/entity/krifto/krifto_female_frosty.png");


    @Override
    public ResourceLocation getTextureLocation(KriftognathusEntity entity) {
        String biomePath = entity.getEntityData().get(KriftognathusEntity.SPAWN_BIOME);
        boolean isMale = entity.isMale();


        if (biomePath.contains("jungle") || biomePath.contains("sparse_jungle")) {
            return isMale ? MALE_JUNGLE : FEMALE_JUNGLE;
        }

        if (biomePath.contains("wooded_badlands") || biomePath.contains("eroded_badlands") || biomePath.contains("badlands")) {
            return isMale ? MALE_ARID : FEMALE_ARID;
        }

        if (biomePath.contains("snowy_taiga") ||  biomePath.contains("grove")) {
            return isMale ? MALE_FROSTY : FEMALE_FROSTY;
        }

        return isMale ? MALE_TEXTURE : FEMALE_TEXTURE;
    }

    @Override
    public void render(KriftognathusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
