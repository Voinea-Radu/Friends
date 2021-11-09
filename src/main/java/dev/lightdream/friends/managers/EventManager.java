package dev.lightdream.friends.managers;

import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.PlayerGUI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;

public class EventManager implements Listener {

    public ArrayList<Player> invseePlayers = new ArrayList<>();

    public EventManager(Main plugin) {
        plugin.getPlugin().getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerOpenMenu(PlayerInteractEntityEvent event) {
        Entity e = event.getRightClicked();

        if (!(e instanceof Player)) {
            return;
        }

        User user = Main.instance.databaseManager.getUser(event.getPlayer());
        User target = Main.instance.databaseManager.getUser((Player) e);

        new PlayerGUI(Main.instance, user, target).open();
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        if (invseePlayers.contains((Player) event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        invseePlayers.remove((Player) event.getPlayer());
    }

}
