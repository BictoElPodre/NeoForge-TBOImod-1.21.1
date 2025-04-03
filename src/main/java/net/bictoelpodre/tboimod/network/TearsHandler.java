package net.bictoelpodre.tboimod.network;

import io.netty.buffer.ByteBuf;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.entity.TearsEntity;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class TearsHandler {

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
                        Level level = context.player().level();
                        if (payload.tearskeydown) {
                            TearsEntity tears = new TearsEntity(player, level);
                            tears.setPos(tears.getX(), tears.getY() - 0.75  , tears.getZ());
                            tears.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                            level.addFreshEntity(tears);
                        }
                    }
                }
        );
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