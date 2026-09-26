package net.bictoelpodre.tboimod.capability;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = "thebindingofisaacmod", bus = net.neoforged.fml.common.EventBusSubscriber.Bus.GAME)
public class CharacterCapabilityProvider {
    
    @SubscribeEvent
    public static void onPlayerJoin(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            var persistentData = player.getPersistentData();
            if (persistentData.contains("tboi_character")) {
                var tag = persistentData.getCompound("tboi_character");
                var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
                if (attachment != null) {
                    attachment.deserializeNBT(tag);
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerSave(net.neoforged.neoforge.event.entity.player.PlayerEvent.SaveToFile event) {
        if (event.getEntity() instanceof Player player) {
            var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
            if (attachment != null) {
                var tag = attachment.serializeNBT();
                player.getPersistentData().put("tboi_character", tag);
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerClone(net.neoforged.neoforge.event.entity.player.PlayerEvent.Clone event) {
        if (event.getEntity() instanceof Player player) {
            var originalAttachment = event.getOriginal().getData(CharacterCapability.CHARACTER_STATS);
            var newAttachment = event.getEntity().getData(CharacterCapability.CHARACTER_STATS);
            if (originalAttachment != null && newAttachment != null) {
                newAttachment.deserializeNBT(originalAttachment.serializeNBT());
            }
        }
    }
}