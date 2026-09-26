package net.bictoelpodre.tboimod.event;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.capability.CharacterCapability;
import net.bictoelpodre.tboimod.character.ModCharacters;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class CharacterAutoApplyHandler {

    private static final String CHARACTER_NBT_KEY = "tboi_selected_character";

    @SubscribeEvent
    public static void onPlayerJoin(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            applySelectedCharacter(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            applySelectedCharacter(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(net.neoforged.neoforge.event.entity.player.PlayerEvent.Clone event) {
        // This handles dimension changes and end portal deaths
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            // The new entity is event.getEntity(), original is event.getOriginal()
            applySelectedCharacter(player);
        }
    }

    private static void applySelectedCharacter(net.minecraft.server.level.ServerPlayer player) {
        String savedId = player.getPersistentData().getString("tboi_selected_character");
        if (!savedId.isEmpty()) {
            var attachment = player.getData(net.bictoelpodre.tboimod.capability.CharacterCapability.CHARACTER_STATS);
            if (attachment != null) {
                var character = net.bictoelpodre.tboimod.character.ModCharacters.getById(savedId);
                attachment.applyCharacter(character);
            }
        }
    }
}