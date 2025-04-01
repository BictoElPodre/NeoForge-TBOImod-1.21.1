package net.bictoelpodre.tboimod.items.custom;

import net.bictoelpodre.tboimod.entity.ModEntities;
import net.bictoelpodre.tboimod.entity.TearsEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.naming.Context;
import java.util.function.Predicate;


public class GlassCannonItem extends BowItem {

    public GlassCannonItem(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {

        if (!level.isClientSide) {

            TearsEntity tears = new TearsEntity(user, level);
            tears.setOwner(user);
            tears.setPos(user.getX(), user.getY() + 1, user.getZ());

            tears.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0f, 1.5f, 0.0f);
            level.addFreshEntity(tears);

            if (user instanceof Player player) {
                player.getCooldowns().addCooldown(stack.getItem(), 400);
            }


        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        player.startUsingItem(hand);
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 30;
    }

}

