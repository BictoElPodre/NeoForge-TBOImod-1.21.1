package net.bictoelpodre.tboimod.entity.custom;

import net.bictoelpodre.tboimod.entity.ModEntities;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;

public class BombEntity extends AbstractArrow {
    private float rotation;
    public Vec2 groundedOffSet;


    public BombEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public BombEntity(LivingEntity shooter, Level level) {
        super (ModEntities.BOMBENTITY.get(),shooter,level, new ItemStack(ModedItems.BOMB.get()),null);

    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModedItems.BOMB.get());
    }

    public float getRenderingRotation() {
        rotation += 0.5F;
        if(rotation >= 360) {
            rotation = 0;
        }
        return this.rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this,this.getOwner()),4);

        if(!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

}