package net.bictoelpodre.tboimod.network;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.entity.TearsEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.UUID;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ServerTearTickHandler {

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        for (UUID playerId : TearsHandler.getShootingPlayers()) {
            ServerPlayer player = event.getServer().getPlayerList().getPlayer(playerId);
            if (player == null || !player.isAlive()) {
                TearsHandler.getShootingPlayers().remove(playerId);
                TearsHandler.getPlayerCooldowns().remove(playerId);
                continue;
            }

            int cooldown = TearsHandler.getPlayerCooldowns().getOrDefault(playerId, 0);
            if (cooldown <= 0) {
                // Fire tear
                ServerLevel level = player.serverLevel();
                TearsEntity tears = new TearsEntity(level);
                tears.setOwner(player);
                tears.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
                tears.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                level.addFreshEntity(tears);

                // Reset cooldown
                TearsHandler.getPlayerCooldowns().put(playerId, TearsHandler.getBaseTearCooldown());
            } else {
                TearsHandler.getPlayerCooldowns().put(playerId, cooldown - 1);
            }
        }
    }
}