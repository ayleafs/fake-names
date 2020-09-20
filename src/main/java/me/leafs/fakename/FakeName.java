package me.leafs.fakename;

import lombok.Getter;
import me.leafs.fakename.commands.NameHandler;
import me.leafs.fakename.utils.ConfigHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "fake-names", name = "Fake Name", version = "1.0")
public class FakeName {
    @Mod.Instance
    public static FakeName instance;

    @Getter private ConfigHandler config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // create the config handler with the recommended config file
        config = new ConfigHandler(event.getSuggestedConfigurationFile());
        config.read();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register the handler command
        ClientCommandHandler.instance.registerCommand(new NameHandler());
    }

    public void setFakeName(String fakeName) {
        // async: save config <3
        new Thread(() -> {
            config.setFakeName(fakeName);
            config.populate();
        }, "FakeName Config").start();
    }
}
