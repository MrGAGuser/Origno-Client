package com.jelly.GhosterBuster.baritone.automine.logging;

import com.jelly.GhosterBuster.GhosterBuster;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;

public class Logger {
    // POV : You think this is a token logger
    public static void log(String msg){
        if(GhosterBuster.config.debugLogMode)
            LogManager.getLogger(GhosterBuster.MODID).info(msg);
    }
    public static void playerLog(String msg){
        if(GhosterBuster.config.debugLogMode)
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[Baritone] : " + msg));
    }
}
