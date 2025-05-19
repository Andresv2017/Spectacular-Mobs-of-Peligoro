package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.darkblade.smopmod.entity.custom.NirasmosaurusEntity;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SMOP.MOD_ID);

    public static final RegistryObject<EntityType<Hell_HippoEntity>> HELL_HIPPO =
            ENTITY_TYPES.register("hell_hippo", () -> EntityType.Builder.of(Hell_HippoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("hell_hippo"));

    public static final RegistryObject<EntityType<TangofteroEntity>> TANGOFTERO =
            ENTITY_TYPES.register("tangoftero", () -> EntityType.Builder.of(TangofteroEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("tangoftero"));

    public static final RegistryObject<EntityType<KriftognathusEntity>> KIFTO =
            ENTITY_TYPES.register("kifto", () -> EntityType.Builder.of(KriftognathusEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("kifto"));

    public static final RegistryObject<EntityType<NirasmosaurusEntity>> NIRAS =
            ENTITY_TYPES.register("niras", () -> EntityType.Builder.of(NirasmosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("niras"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
