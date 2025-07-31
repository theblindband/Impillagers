package com.impillagers.mod.util;
//TODO: FIX
/*mport net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record HudOverlayOpacityPayload(float opacity) implements CustomPayload {
    public static final CustomPayload.Id<HudOverlayOpacityPayload> ID = new CustomPayload.Id<>(ModNetworking.UPDATE_OVERLAY_OPACITY);
    public static final PacketCodec<RegistryByteBuf, HudOverlayOpacityPayload> CODEC = PacketCodec.tuple(PacketCodecs.FLOAT, HudOverlayOpacityPayload::opacity, HudOverlayOpacityPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
*/