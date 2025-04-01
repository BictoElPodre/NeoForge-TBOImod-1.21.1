package net.bictoelpodre.tboimod.entity;


import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class TearsEntity extends AbstractArrow {
    public TearsEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public TearsEntity(LivingEntity shooter, Level level) {
        super(ModEntities.TEARS_ENTITY.get(), shooter, level, ItemStack.EMPTY, new ItemStack(ModedItems.GLASS_CANNON.get()));
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()),10);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();

        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
    }

    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
        super.playSound(SoundEvents.PLAYER_SPLASH, volume, pitch);
    }
}

