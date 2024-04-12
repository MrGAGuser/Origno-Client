package com.jelly.GhosterBuster.mixins.network;

import com.jelly.GhosterBuster.GhosterBuster;
import com.jelly.GhosterBuster.handlers.MacroHandler;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {


    @Inject(method = "handlePlayerListHeaderFooter", at = @At("HEAD"))
    public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packetIn, CallbackInfo ci) {
        MacroHandler.gameState.header = packetIn.getHeader().getFormattedText().length() == 0 ? null : packetIn.getHeader();
        MacroHandler.gameState.footer = packetIn.getFooter().getFormattedText().length() == 0 ? null : packetIn.getFooter();
        MacroHandler.gameState.update();

    }

}
