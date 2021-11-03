package yt.sehrschlecht.voidfall;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import yt.sehrschlecht.voidfall.commands.VoidFallCommand;
import yt.sehrschlecht.voidfall.listeners.VoidListeners;

public final class VoidFall extends JavaPlugin {
    private static VoidFall plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        getCommand("voidfall").setExecutor(new VoidFallCommand());
        getCommand("voidfall").setTabCompleter(new VoidFallCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new VoidListeners(), this);
    }

    @Override
    public void onDisable() {

    }

    public static VoidFall getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return "§7[§bVoidFall§7] ";
    }
}
