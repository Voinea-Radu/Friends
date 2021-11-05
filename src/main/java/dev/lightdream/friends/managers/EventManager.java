package dev.lightdream.friends.managers;

import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.PlayerGUI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventManager implements Listener {

    public EventManager(Main plugin){
        plugin.getPlugin().getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerOpenMenu(PlayerInteractEntityEvent event){
        Entity e = event.getRightClicked();

        if(!(e instanceof Player)){
            return;
        }

        User user = Main.instance.databaseManager.getUser(event.getPlayer());
        User target = Main.instance.databaseManager.getUser((Player) e);

        new PlayerGUI(Main.instance, user, target).open();
    }

}
