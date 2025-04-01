package net.bictoelpodre.tboimod.entity;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Tier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TheBindingOfIsaacMod.MOD_ID);

    public static final Supplier<EntityType<TearsEntity>> TEARS_ENTITY =
            ENTITIES.register("tears_entity", () -> EntityType.Builder.<TearsEntity>of(TearsEntity::new, MobCategory.MISC)
                    .sized(1f,1f).build("tears_entity"));

    public static void register(IEventBus eventBus) { ENTITIES.register(eventBus); }
}