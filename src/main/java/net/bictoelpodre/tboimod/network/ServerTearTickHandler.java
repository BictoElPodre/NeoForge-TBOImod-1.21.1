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
                
                // Spawn ligeramente delante del jugador (0.5 bloques adelante) para evitar colisión inicial
                double spawnX = player.getX() + Math.sin(Math.toRadians(-player.getYRot())) * 0.5;
                double spawnZ = player.getZ() + Math.cos(Math.toRadians(-player.getYRot())) * 0.5;
                tears.setPos(spawnX, player.getEyeY() - 0.1, spawnZ);
                
                // Obtener stats del personaje para determinar velocidad y range
                var attachment = player.getData(net.bictoelpodre.tboimod.capability.CharacterCapability.CHARACTER_STATS);
                float shotSpeed = attachment != null ? attachment.getBaseShotSpeed() : 1.0f;
                float range = attachment != null ? attachment.getBaseRange() : 6.5f;
                
                // DEBUG
                System.out.println("[TBOI DEBUG] Spawning tear for " + player.getName().getString() + 
                    " shotSpeed=" + shotSpeed + " range=" + range + 
                    " velocity=" + (1.5F * shotSpeed) + " pos=(" + spawnX + "," + spawnZ + ")");
                
                // Calcular velocidad basada en shot speed (factor 1.5 = velocidad base vanilla)
                float velocity = 1.5F * shotSpeed;
                
                tears.setOwner(player);
                tears.setPos(spawnX, player.getEyeY() - 0.1, spawnZ);
                tears.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity, 0.0F);
                
                // Pasar range para calcular lifetime
                tears.setTearRange(range);
                
                level.addFreshEntity(tears);

                // Reset cooldown
                TearsHandler.getPlayerCooldowns().put(playerId, TearsHandler.getBaseTearCooldown());
            } else {
                TearsHandler.getPlayerCooldowns().put(playerId, cooldown - 1);
            }
        }
    }
}