package net.bictoelpodre.tboimod.capability;

import net.bictoelpodre.tboimod.character.ModCharacter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.AttachmentType.Builder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class CharacterCapability {
    
    public interface ICharacterStats {
        String getCharacterId();
        void setCharacterId(String id);
        
        float getBaseDamage();
        void setBaseDamage(float value);
        
        float getBaseTearRate();
        void setBaseTearRate(float value);
        
        float getBaseRange();
        void setBaseRange(float value);
        
        float getBaseShotSpeed();
        void setBaseShotSpeed(float value);
        
        float getBaseSpeed();
        void setBaseSpeed(float value);
        
        float getBaseLuck();
        void setBaseLuck(float value);
        
        int getMaxHealth();
        void setMaxHealth(int value);
        
        int getMaxSoulHearts();
        void setMaxSoulHearts(int value);
        
        int getMaxBlackHearts();
        void setMaxBlackHearts(int value);
        
        float getTearDelay();
        float getFireRate();
        float getEffectiveRange();
        
        void applyCharacter(ModCharacter character);
        
        CompoundTag serializeNBT();
        void deserializeNBT(CompoundTag tag);
    }

    public static class CharacterStats implements ICharacterStats {
        private String characterId = "isaac";
        private float baseDamage = 3.5f;
        private float baseTearRate = 6f;
        private float baseRange = 6.5f;
        private float baseShotSpeed = 1f;
        private float baseSpeed = 1f;
        private float baseLuck = 0f;
        private int maxHealth = 6;
        private int maxSoulHearts = 0;
        private int maxBlackHearts = 0;

        @Override
        public String getCharacterId() {
            return characterId;
        }

        @Override
        public void setCharacterId(String id) {
            this.characterId = id;
        }

        @Override
        public float getBaseDamage() { return baseDamage; }
        @Override
        public void setBaseDamage(float value) { this.baseDamage = value; }

        @Override
        public float getBaseTearRate() { return baseTearRate; }
        @Override
        public void setBaseTearRate(float value) { this.baseTearRate = value; }

        @Override
        public float getBaseRange() { return baseRange; }
        @Override
        public void setBaseRange(float value) { this.baseRange = value; }

        @Override
        public float getBaseShotSpeed() { return baseShotSpeed; }
        @Override
        public void setBaseShotSpeed(float value) { this.baseShotSpeed = value; }

        @Override
        public float getBaseSpeed() { return baseSpeed; }
        @Override
        public void setBaseSpeed(float value) { this.baseSpeed = value; }

        @Override
        public float getBaseLuck() { return baseLuck; }
        @Override
        public void setBaseLuck(float value) { this.baseLuck = value; }

        @Override
        public int getMaxHealth() { return maxHealth; }
        @Override
        public void setMaxHealth(int value) { this.maxHealth = value; }

        @Override
        public int getMaxSoulHearts() { return maxSoulHearts; }
        @Override
        public void setMaxSoulHearts(int value) { this.maxSoulHearts = value; }

        @Override
        public int getMaxBlackHearts() { return maxBlackHearts; }
        @Override
        public void setMaxBlackHearts(int value) { this.maxBlackHearts = value; }

        @Override
        public float getTearDelay() {
            return ModCharacter.calculateTearDelay(baseTearRate);
        }

        @Override
        public float getFireRate() {
            return ModCharacter.calculateFireRate(baseTearRate);
        }

        @Override
        public float getEffectiveRange() {
            return ModCharacter.calculateEffectiveRange(baseRange);
        }

        @Override
        public void applyCharacter(ModCharacter character) {
            this.characterId = character.id;
            this.baseDamage = character.baseDamage;
            this.baseTearRate = character.baseTearRate;
            this.baseRange = character.baseRange;
            this.baseShotSpeed = character.baseShotSpeed;
            this.baseSpeed = character.baseSpeed;
            this.baseLuck = character.baseLuck;
            this.maxHealth = character.maxHealth;
            this.maxSoulHearts = character.maxSoulHearts;
            this.maxBlackHearts = character.maxBlackHearts;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putString("characterId", characterId);
            tag.putFloat("baseDamage", baseDamage);
            tag.putFloat("baseTearRate", baseTearRate);
            tag.putFloat("baseRange", baseRange);
            tag.putFloat("baseShotSpeed", baseShotSpeed);
            tag.putFloat("baseSpeed", baseSpeed);
            tag.putFloat("baseLuck", baseLuck);
            tag.putInt("maxHealth", maxHealth);
            tag.putInt("maxSoulHearts", maxSoulHearts);
            tag.putInt("maxBlackHearts", maxBlackHearts);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            this.characterId = tag.getString("characterId");
            this.baseDamage = tag.getFloat("baseDamage");
            this.baseTearRate = tag.getFloat("baseTearRate");
            this.baseRange = tag.getFloat("baseRange");
            this.baseShotSpeed = tag.getFloat("baseShotSpeed");
            this.baseSpeed = tag.getFloat("baseSpeed");
            this.baseLuck = tag.getFloat("baseLuck");
            this.maxHealth = tag.getInt("maxHealth");
            this.maxSoulHearts = tag.getInt("maxSoulHearts");
            this.maxBlackHearts = tag.getInt("maxBlackHearts");
        }
    }

    // Attachment type for character stats
    public static final net.neoforged.neoforge.attachment.AttachmentType<net.bictoelpodre.tboimod.capability.CharacterCapability.ICharacterStats> CHARACTER_STATS = 
        net.neoforged.neoforge.attachment.AttachmentType.<net.bictoelpodre.tboimod.capability.CharacterCapability.ICharacterStats>builder(() -> new CharacterStats())
            .build();

    // Deferred register for attachment types
    public static final net.neoforged.neoforge.registries.DeferredRegister<net.neoforged.neoforge.attachment.AttachmentType<?>> ATTACHMENT_TYPES = 
        net.neoforged.neoforge.registries.DeferredRegister.create(net.neoforged.neoforge.registries.NeoForgeRegistries.ATTACHMENT_TYPES, "thebindingofisaacmod");
    
    public static final net.neoforged.neoforge.registries.DeferredHolder<net.neoforged.neoforge.attachment.AttachmentType<?>, net.neoforged.neoforge.attachment.AttachmentType<net.bictoelpodre.tboimod.capability.CharacterCapability.ICharacterStats>> CHARACTER_STATS_HOLDER = 
        ATTACHMENT_TYPES.register("character_stats", () -> CHARACTER_STATS);
    
    public static void registerCapabilities(net.neoforged.bus.api.IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}