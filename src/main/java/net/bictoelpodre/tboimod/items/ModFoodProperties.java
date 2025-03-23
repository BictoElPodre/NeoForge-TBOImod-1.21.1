package net.bictoelpodre.tboimod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties ROTTENMEAT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).usingConvertsTo(Items.BONE)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200, 0), 1.0F).build();

    public static final FoodProperties BREAKFAST = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0F).build();
}
