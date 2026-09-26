package net.bictoelpodre.tboimod.character;

import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.util.Lazy;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class ModCharacter {
    public final String id;
    public final String displayName;
    public final ResourceLocation portraitTexture;
    public final ResourceLocation hudTexture;
    public final float baseDamage;
    public final float baseTearRate; // tears per second (base ~6)
    public final float baseRange;
    public final float baseShotSpeed;
    public final float baseSpeed;
    public final float baseLuck;
    public final int maxHealth; // in half-hearts (red hearts)
    public final int maxSoulHearts;
    public final int maxBlackHearts;
    public final List<ItemStack> startingItems;
    public final Supplier<Lazy<ItemStack>> unlockItem; // item that unlocks this character

    private ModCharacter(Builder builder) {
        this.id = builder.id;
        this.displayName = builder.displayName;
        this.portraitTexture = builder.portraitTexture;
        this.hudTexture = builder.hudTexture;
        this.baseDamage = builder.baseDamage;
        this.baseTearRate = builder.baseTearRate;
        this.baseRange = builder.baseRange;
        this.baseShotSpeed = builder.baseShotSpeed;
        this.baseSpeed = builder.baseSpeed;
        this.baseLuck = builder.baseLuck;
        this.maxHealth = builder.maxHealth;
        this.maxSoulHearts = builder.maxSoulHearts;
        this.maxBlackHearts = builder.maxBlackHearts;
        this.startingItems = builder.startingItems;
        this.unlockItem = builder.unlockItem;
    }

    /**
     * Calculate tear delay from tear rate (TBOI formula)
     * Tear Delay = max(1, 16 - floor(tearRate))
     * Fire Rate = 30 / (tearDelay + 1)
     */
    public static float calculateTearDelay(float tearRate) {
        return Math.max(1, 16 - (int)Math.floor(tearRate));
    }

    public static float calculateFireRate(float tearRate) {
        float tearDelay = calculateTearDelay(tearRate);
        return 30f / (tearDelay + 1);
    }

    /**
     * Calculate effective range from range stat
     * TBOI: Range = 6.5 + rangeStat * 1.5 (approx)
     */
    public static float calculateEffectiveRange(float rangeStat) {
        return 6.5f + rangeStat * 1.5f;
    }

    /**
     * Apply character stats to player
     */
    public void applyToPlayer(net.minecraft.world.entity.player.Player player) {
        // Give starting items
        for (ItemStack stack : startingItems) {
            if (!player.getInventory().add(stack.copy())) {
                player.drop(stack.copy(), false);
            }
        }

        // Set max health (red hearts)
        var healthAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (healthAttr != null) {
            healthAttr.setBaseValue(maxHealth / 2.0);
        }
        player.setHealth(maxHealth / 2.0f);

        // Apply soul hearts
        if (maxSoulHearts > 0) {
            // Soul hearts are handled via player data
            // This would need a custom capability or effect
        }

        // Apply black hearts
        if (maxBlackHearts > 0) {
            // Black hearts handled similarly
        }

        // Store character stats on player for later use
        // This would be done via a capability or player data
    }

    /**
     * Get calculated stats for display/UI
     */
    public float getTearDelay() {
        return calculateTearDelay(baseTearRate);
    }

    public float getFireRate() {
        return calculateFireRate(baseTearRate);
    }

    public float getEffectiveRange() {
        return calculateEffectiveRange(baseRange);
    }

    public float getEffectiveDamage() {
        return baseDamage;
    }

    public float getEffectiveSpeed() {
        return baseSpeed;
    }

    public float getEffectiveShotSpeed() {
        return baseShotSpeed;
    }

    public float getEffectiveLuck() {
        return baseLuck;
    }

    public static class Builder {
        private String id;
        private String displayName;
        private ResourceLocation portraitTexture;
        private ResourceLocation hudTexture;
        private float baseDamage = 3.5f;
        private float baseTearRate = 6f;
        private float baseRange = 6.5f;
        private float baseShotSpeed = 1f;
        private float baseSpeed = 1f;
        private float baseLuck = 0f;
        private int maxHealth = 6; // 3 hearts
        private int maxSoulHearts = 0;
        private int maxBlackHearts = 0;
        private List<ItemStack> startingItems = List.of();
        private Supplier<Lazy<ItemStack>> unlockItem = null;

        public Builder(String id, String displayName, ResourceLocation portraitTexture) {
            this.id = id;
            this.displayName = displayName;
            this.portraitTexture = portraitTexture;
            this.hudTexture = portraitTexture;
        }

        public Builder hudTexture(ResourceLocation texture) {
            this.hudTexture = texture;
            return this;
        }

        public Builder baseDamage(float damage) {
            this.baseDamage = damage;
            return this;
        }

        public Builder baseTearRate(float rate) {
            this.baseTearRate = rate;
            return this;
        }

        public Builder baseRange(float range) {
            this.baseRange = range;
            return this;
        }

        public Builder baseShotSpeed(float speed) {
            this.baseShotSpeed = speed;
            return this;
        }

        public Builder baseSpeed(float speed) {
            this.baseSpeed = speed;
            return this;
        }

        public Builder baseLuck(float luck) {
            this.baseLuck = luck;
            return this;
        }

        public Builder maxHealth(int halfHearts) {
            this.maxHealth = halfHearts;
            return this;
        }

        public Builder maxSoulHearts(int halfHearts) {
            this.maxSoulHearts = halfHearts;
            return this;
        }

        public Builder maxBlackHearts(int halfHearts) {
            this.maxBlackHearts = halfHearts;
            return this;
        }

        public Builder startingItems(List<ItemStack> items) {
            this.startingItems = items;
            return this;
        }

        public Builder startingItem(ItemStack item) {
            this.startingItems = List.of(item);
            return this;
        }

        public Builder unlockItem(Supplier<Lazy<ItemStack>> item) {
            this.unlockItem = item;
            return this;
        }

        public ModCharacter build() {
            return new ModCharacter(this);
        }
    }
}