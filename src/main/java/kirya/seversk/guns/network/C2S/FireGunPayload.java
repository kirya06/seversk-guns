package kirya.seversk.guns.network.C2S;

import kirya.seversk.guns.SeverskGuns;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

public record FireGunPayload(byte dummy) implements CustomPacketPayload {

    public static final Identifier SUMMON_LIGHTNING_PAYLOAD_ID = Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "fire_gun");
    public static final CustomPacketPayload.Type<FireGunPayload> ID = new CustomPacketPayload.Type<FireGunPayload>(SUMMON_LIGHTNING_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, FireGunPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BYTE, FireGunPayload::dummy, FireGunPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
