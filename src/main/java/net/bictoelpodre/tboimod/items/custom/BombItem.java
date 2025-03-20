package net.bictoelpodre.tboimod.items.custom;

import net.bictoelpodre.tboimod.entity.custom.BombEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombItem extends Item {
    public BombItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(),
                SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL,0.5F,0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            BombEntity bombProjectile = new BombEntity(pPlayer, pLevel);
            bombProjectile.shootFromRotation(pPlayer,pPlayer.getXRot(),pPlayer.getYRot(),0.0F,1.5F,0F);
            pLevel.addFreshEntity(bombProjectile);

        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemStack.shrink(1);
        }


        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }
}
