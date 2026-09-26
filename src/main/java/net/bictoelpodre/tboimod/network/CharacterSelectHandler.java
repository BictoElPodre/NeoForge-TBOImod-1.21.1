package net.bictoelpodre.tboimod.network;

import io.netty.buffer.ByteBuf;
import net.bictoelpodre.tboimod.capability.CharacterCapability;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.character.ModCharacters;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CharacterSelectHandler {

    private static final String CHARACTER_NBT_KEY = "tboi_selected_character";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");
        registrar.playToServer(
                CharacterSelectPacket.TYPE,
                CharacterSelectPacket.STREAM_CODEC,
                new IPayloadHandler<CharacterSelectPacket>() {
                    @Override
                    public void handle(CharacterSelectPacket payload, IPayloadContext context) {
                        ServerPlayer player = (ServerPlayer) context.player();
                        ModCharacters.getById(payload.characterId()).applyToPlayer(player);
                        player.getPersistentData().putString("tboi_selected_character", payload.characterId());
                        // Also update the capability
                        var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
                        if (attachment != null) {
                            var character = ModCharacters.getById(payload.characterId());
                            attachment.applyCharacter(character);
                        }
                    }
                }
        );
    }

    public record CharacterSelectPacket(String characterId) implements CustomPacketPayload {
        public static final Type<CharacterSelectPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("tboimod", "character_select"));
        
        public static final StreamCodec<ByteBuf, CharacterSelectPacket> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                CharacterSelectPacket::characterId,
                CharacterSelectPacket::new
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}