package net.bictoelpodre.tboimod.items.custom;

import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AcidTearsItems extends Item {

    private final int blindnessDuration;
    private final int cooldownDuration;

    public AcidTearsItems(Properties properties,int blindnessDuration,int cooldownDuration)
    {
        super(properties);
        this.blindnessDuration = blindnessDuration;
        this.cooldownDuration = cooldownDuration;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (!level.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, blindnessDuration, 0));
            level.playSound(null,player.getX(),player.getY(),player.getZ(),
                    SoundEvents.SAND_BREAK, player.getSoundSource(),1.0F,0.5F);
            player.getCooldowns().addCooldown(ModedItems.SULFURICDUST.get(), cooldownDuration);
            player.getCooldowns().addCooldown(ModedItems.SULFURDUST.get(), cooldownDuration);
            player.getCooldowns().addCooldown(ModedItems.SULFURROCK.get(), cooldownDuration);
            player.getCooldowns().addCooldown(ModedItems.SULFURICACID.get(), cooldownDuration);
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }
        }
        return InteractionResultHolder.success(itemStack.isEmpty() ? ItemStack.EMPTY : itemStack);
    }
}
