package yt.sehrschlecht.voidfall.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import yt.sehrschlecht.voidfall.config.Config;

public class VoidListeners implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(!event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) return;
        Player player = (Player) event.getEntity();
        Config config = Config.getInstance();

        event.setCancelled(config.shouldCancelDamage());

        Location location = player.getLocation().clone();
        if(config.shouldChangeDimensions()) {
            if(location.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                location.setWorld(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment().equals(World.Environment.THE_END)).findFirst().orElse(location.getWorld()));
            } else if(location.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                location.setWorld(Bukkit.getWorlds().stream().filter(w -> w.getEnvironment().equals(World.Environment.NORMAL)).findFirst().orElse(location.getWorld()));
            }
        }
        location.setY(config.getYHeight());
        player.teleport(location);

        if(config.shouldResetFallDistance()) {
            player.setFallDistance(0F);
        }

        if(config.shouldPlaySound()) {
            player.playSound(player.getLocation(), config.getSound(), config.getVolume(), config.getPitch());
        }
    }
}
