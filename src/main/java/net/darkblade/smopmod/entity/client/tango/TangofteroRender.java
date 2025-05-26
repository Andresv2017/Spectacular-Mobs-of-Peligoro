package net.darkblade.smopmod.entity.client.tango;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.TangofteroVariant;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class TangofteroRender extends MobRenderer<TangofteroEntity, TangofteroModel<TangofteroEntity>> {

    private static final Map<TangofteroVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(TangofteroVariant.class), map -> {
                map.put(TangofteroVariant.BLACK,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_black.png"));
                map.put(TangofteroVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_blue.png"));
                map.put(TangofteroVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_red.png"));
                map.put(TangofteroVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_brown.png"));
                map.put(TangofteroVariant.CREAM,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_cream.png"));
                map.put(TangofteroVariant.LAVANDER,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_lavander.png"));
                map.put(TangofteroVariant.SILVER,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_silver.png"));
                map.put(TangofteroVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_white.png"));
                map.put(TangofteroVariant.YELLOW,
                        ResourceLocation.fromNamespaceAndPath(SMOP.MOD_ID, "textures/entity/tangoftero/tangoftero_yellow.png"));
            });

    public TangofteroRender(EntityRendererProvider.Context pContext) {
        super(pContext, new TangofteroModel<TangofteroEntity>(pContext.bakeLayer(ModModelLayers.TANGOFTERO_LAYER)),.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(TangofteroEntity tangofteroEntity) {
        return LOCATION_BY_VARIANT.get(tangofteroEntity.getVariant());
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
