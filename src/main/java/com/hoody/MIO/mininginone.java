package com.jelly.GhosterBuster;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jelly.GhosterBuster.command.AOTVWaypointsCommands;
import com.jelly.GhosterBuster.command.BaritoneDebug;
import com.jelly.GhosterBuster.config.Config;
import com.jelly.GhosterBuster.config.aotv.AOTVWaypoints;
import com.jelly.GhosterBuster.config.aotv.AOTVWaypointsStructs;
import com.jelly.GhosterBuster.features.*;
import com.jelly.GhosterBuster.handlers.KeybindHandler;
import com.jelly.GhosterBuster.handlers.MacroHandler;
import com.jelly.GhosterBuster.utils.HypixelUtils.SkyblockInfo;
import com.jelly.GhosterBuster.waypoints.WaypointHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

@Mod(name = "GhosterBuster", modid = GhosterBuster.MODID, version = GhosterBuster.VERSION)
public class GhosterBuster {
    public static final String MODID = "GhosterBuster";
    public static final String VERSION = "1.0";
    public static Gson gson;

    public static Config config;

    //TODO: fix executor service
    //public static ExecutorService pathfindPool = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("PathFinderPool-%d").build());

    public static MobKiller mobKiller = new MobKiller();

    public static AOTVWaypoints aotvWaypoints;

    public void createNewWaypointsConfig(FMLPreInitializationEvent event) {
        File directory = new File(event.getModConfigurationDirectory().getAbsolutePath());
        File coordsFile = new File(directory, "aotv_coords_mm.json");

        if (!coordsFile.exists()) {
            try {
                Files.createFile(Paths.get(coordsFile.getPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Reader reader = Files.newBufferedReader(Paths.get("./config/aotv_coords_mm.json"));
            aotvWaypoints = gson.fromJson(reader, AOTVWaypoints.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (aotvWaypoints != null) {
            System.out.println(aotvWaypoints.getRoutes());
        } else {
            System.out.println("aotvWaypoints is null");
            System.out.println("Creating new CoordsConfig");
            aotvWaypoints = new AOTVWaypoints();
            AOTVWaypointsStructs.SaveWaypoints();
            System.out.println(aotvWaypoints.getRoutes());
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        createNewWaypointsConfig(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config = new Config();

        if(System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH).contains("mac") || System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH).contains("darwin"))
            registerInitNotification();

        MinecraftForge.EVENT_BUS.register(new MacroHandler());
        MinecraftForge.EVENT_BUS.register(new WaypointHandler());
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new SkyblockInfo());
        MinecraftForge.EVENT_BUS.register(new FuelFilling());
        MinecraftForge.EVENT_BUS.register(mobKiller);
        MinecraftForge.EVENT_BUS.register(new Autosell());
        MinecraftForge.EVENT_BUS.register(new Failsafes());
        MinecraftForge.EVENT_BUS.register(new AOTVWaypointsCommands());
        MinecraftForge.EVENT_BUS.register(new PlayerESP());
        MinecraftForge.EVENT_BUS.register(new PingAlert());
        KeybindHandler.initializeCustomKeybindings();
        MacroHandler.initializeMacro();

        ClientCommandHandler.instance.registerCommand(new BaritoneDebug());

        Minecraft.getMinecraft().gameSettings.gammaSetting = 100;
    }

    public static void registerInitNotification() {
        new Thread(() -> {
            TrayIcon trayIcon = new TrayIcon(new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR), "Farm Helper Failsafe Notification");
            trayIcon.setToolTip("Farm Helper Failsafe Notification");
            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            trayIcon.displayMessage("Farm Helper Failsafe Notification", "Register Notifications", TrayIcon.MessageType.INFO);
            SystemTray.getSystemTray().remove(trayIcon);
        }).start();
    }

}
