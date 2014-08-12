package org.entityapi.test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.entityapi.api.EntityManager;
import org.entityapi.api.plugin.EntityAPI;

public class Main extends JavaPlugin {

    private EntityManager entityManager;

    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("EntityAPI")) {
            getLogger().info("EntityAPI is not enabled! -> disabling");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.entityManager = EntityAPI.createManager(this);

    }

    @Override
    public void onDisable() {

    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
