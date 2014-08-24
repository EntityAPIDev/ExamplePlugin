package org.entityapi.test;

import com.dsh105.command.CommandManager;
import com.dsh105.command.PluginCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.entityapi.api.EntityManager;
import org.entityapi.api.plugin.EntityAPI;

public class Main extends JavaPlugin {

    private static Main INSTANCE;

    private EntityManager entityManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        if (!Bukkit.getPluginManager().isPluginEnabled("EntityAPI")) {
            getLogger().info("EntityAPI is not enabled! -> disabling");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.entityManager = EntityAPI.createManager(this);

        this.commandManager = new PluginCommandManager<>(this);
        this.commandManager.register(new EntityCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerMoveListener(this), this);
    }

    @Override
    public void onDisable() {
        INSTANCE = null;

    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
