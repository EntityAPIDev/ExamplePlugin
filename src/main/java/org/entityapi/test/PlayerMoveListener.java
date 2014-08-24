package org.entityapi.test;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.entityapi.api.EntityManager;
import org.entityapi.api.entity.ControllableEntity;

public class PlayerMoveListener implements Listener {

    private final Main main;

    public PlayerMoveListener(Main main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        EntityManager manager = this.main.getEntityManager();
        Location to = event.getPlayer().getLocation();
        for (ControllableEntity entity : manager.getEntities()) {
            if (isInRadius(entity, to, 10.0))
                entity.lookAt(event.getPlayer());
        }
    }

    private boolean isInRadius(ControllableEntity entity, Location location, double r) {
        double radiusSquared = r * r;
        if (entity.getBukkitEntity().getLocation().distanceSquared(location) < radiusSquared)
            return true;

        return false;
    }
}
