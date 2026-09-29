package net.bictoelpodre.tboimod.entity;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TheBindingOfIsaacMod.MOD_ID);

    public static final Supplier<EntityType<TearsEntity>> TEARS_ENTITY =
            ENTITIES.register("tears_entity", () -> EntityType.Builder.<TearsEntity>of(TearsEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(64) // Increased tracking range for visibility
                    .updateInterval(1) // Update every tick for smooth movement
                    .build("tears_entity"));

    public static void register(IEventBus eventBus) { ENTITIES.register(eventBus); }
}