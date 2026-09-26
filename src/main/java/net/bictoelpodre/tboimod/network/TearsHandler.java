package net.bictoelpodre.tboimod.network;

import io.netty.buffer.ByteBuf;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.entity.TearsEntity;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class TearsHandler {

    // Track players currently holding the shoot key
    private static final Set<UUID> SHOOTING_PLAYERS = new HashSet<>();
    // Cooldown per player (ticks until next tear)
    private static final java.util.Map<UUID, Integer> PLAYER_COOLDOWNS = new java.util.HashMap<>();

    // Base tear fire rate (ticks between tears) - TBOI default ~10 ticks (6 tears/sec)
    private static final int BASE_TEAR_COOLDOWN = 10;

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");
        registrar.playToServer(
                IsTearsKeyPressed.IS_TEARS_KEY_PRESSED_TYPE,
                IsTearsKeyPressed.IS_TEARS_KEY_PRESSED_STREAM_CODEC,
                new IPayloadHandler<IsTearsKeyPressed>() {
                    @Override
                    public void handle(IsTearsKeyPressed payload, IPayloadContext context) {
                        Player player = context.player();
                        if (payload.tearskeydown) {
                            SHOOTING_PLAYERS.add(player.getUUID());
                        } else {
                            SHOOTING_PLAYERS.remove(player.getUUID());
                            // Don't remove cooldown on key release - keeps fire rate consistent
                        }
                    }
                }
        );
    }

    public static Set<UUID> getShootingPlayers() {
        return SHOOTING_PLAYERS;
    }

    public static java.util.Map<UUID, Integer> getPlayerCooldowns() {
        return PLAYER_COOLDOWNS;
    }

    public static int getBaseTearCooldown() {
        return BASE_TEAR_COOLDOWN;
    }

    public record IsTearsKeyPressed(boolean tearskeydown) implements CustomPacketPayload {

        public static final CustomPacketPayload.Type<IsTearsKeyPressed> IS_TEARS_KEY_PRESSED_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("tboimod", "istearskeypressed"));

        public static final StreamCodec<ByteBuf, IsTearsKeyPressed> IS_TEARS_KEY_PRESSED_STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL,
                IsTearsKeyPressed::tearskeydown,
                IsTearsKeyPressed::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return IS_TEARS_KEY_PRESSED_TYPE;
        }
    }
}