package net.bictoelpodre.tboimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PayloadsRegistrar {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                OnKeyBind.KEY_BIND_TYPE,
                OnKeyBind.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleDataOnMain,
                        ServerPayloadHandler::handleDataOnMain
                )
        );
    }

    public record OnKeyBind (boolean istearskeypressed) implements CustomPacketPayload {
        public static final  CustomPacketPayload.Type<OnKeyBind> KEY_BIND_TYPE =
                new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("tboimod", "onkeybind"));

        public static final StreamCodec<ByteBuf, OnKeyBind> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL,
                OnKeyBind::istearskeypressed,
                OnKeyBind::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return KEY_BIND_TYPE;
        }

    }
}
