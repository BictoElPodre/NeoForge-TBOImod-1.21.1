package net.bictoelpodre.tboimod.entity;


import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.nbt.CompoundTag;
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
    private static final EntityDataAccessor<Float> DATA_TEAR_RANGE = SynchedEntityData.defineId(TearsEntity.class, EntityDataSerializers.FLOAT);

    private int maxLifetime = 100; // Default fallback (5 seconds)
    private int lifetime = 0;

    public TearsEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public TearsEntity(LivingEntity shooter, Level level) {
        super(ModEntities.TEARS_ENTITY.get(), shooter, level, new ItemStack(ModedItems.GLASS_CANNON.get()), ItemStack.EMPTY);
    }

    // Constructor for network spawning (no weapon required)
    public TearsEntity(Level level) {
        super(ModEntities.TEARS_ENTITY.get(), level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TEAR_RANGE, 6.5f);
    }

    public void setTearRange(float range) {
        this.entityData.set(DATA_TEAR_RANGE, range);
        updateMaxLifetime();
        System.out.println("[TBOI DEBUG TearsEntity] setTearRange: range=" + range + " maxLifetime=" + maxLifetime);
    }

    public float getTearRange() {
        return this.entityData.get(DATA_TEAR_RANGE);
    }

    private void updateMaxLifetime() {
        float range = this.entityData.get(DATA_TEAR_RANGE);
        // Calcular lifetime en ticks: range * 20 ticks, mínimo 100 (5s), máximo 600 (30s)
        // Isaac base range 6.5 = 130 ticks (6.5s) - tiempo suficiente para 10-15 bloques
        this.maxLifetime = Math.max(100, Math.min(600, (int)(range * 20)));
        this.lifetime = 0;
        System.out.println("[TBOI DEBUG TearsEntity] updateMaxLifetime: range=" + range + " maxLifetime=" + maxLifetime);
    }

    @Override
    public void tick() {
        super.tick();
        lifetime++;
        // Safety: ensure maxLifetime is never 0 (client may tick before sync)
        if (maxLifetime <= 0) {
            maxLifetime = 100;
        }
        if (lifetime % 20 == 0) { // Log cada 20 ticks
            System.out.println("[TBOI DEBUG TearsEntity] tick: lifetime=" + lifetime + "/" + maxLifetime + " pos=(" + getX() + "," + getY() + "," + getZ() + ")");
        }
        if (lifetime >= maxLifetime) {
            System.out.println("[TBOI DEBUG TearsEntity] DISCARDING - lifetime=" + lifetime + "/" + maxLifetime);
            this.discard();
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (key.equals(DATA_TEAR_RANGE)) {
            System.out.println("[TBOI DEBUG TearsEntity] Synced data updated, updating maxLifetime");
            updateMaxLifetime();
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        
        // No dañar al owner (jugador que disparó) - inmunidad durante los primeros 10 ticks
        if (entity == this.getOwner() && lifetime < 10) {
            return;
        }
        
        // No dañar al owner nunca
        if (entity == this.getOwner()) {
            return;
        }
        
        super.onHitEntity(result);
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
        super.playSound(SoundEvents.PLAYER_SPLASH, volume, pitch);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        // Don't call super.addAdditionalSaveData() - it tries to save the weapon ItemStack which is empty
        // Save only our custom data
        compound.putInt("tearLifetime", lifetime);
        compound.putInt("tearMaxLifetime", maxLifetime);
        compound.putFloat("tearRange", getTearRange());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        // Don't call super.readAdditionalSaveData() - it tries to read the weapon ItemStack
        this.lifetime = compound.getInt("tearLifetime");
        this.maxLifetime = compound.getInt("tearMaxLifetime");
        this.entityData.set(DATA_TEAR_RANGE, compound.getFloat("tearRange"));
    }
}