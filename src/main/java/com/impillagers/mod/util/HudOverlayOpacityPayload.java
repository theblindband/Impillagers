package com.impillagers.mod.util;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public record HudOverlayOpacityPayload(float opacity) {
    public static final Identifier PACKET_ID = new Identifier("impillagers", "hud_overlay_opacity");

    public PacketByteBuf write(PacketByteBuf buf) {
        buf.writeFloat(opacity);
        return buf;
    }

    public static HudOverlayOpacityPayload read(PacketByteBuf buf) {
        return new HudOverlayOpacityPayload(buf.readFloat());
    }
}