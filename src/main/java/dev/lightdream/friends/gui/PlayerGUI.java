package dev.lightdream.friends.gui;

import dev.lightdream.api.IAPI;
import dev.lightdream.api.dto.GUIConfig;
import dev.lightdream.api.dto.GUIItem;
import dev.lightdream.api.gui.GUI;
import dev.lightdream.api.managers.PAPI;
import dev.lightdream.api.utils.MessageBuilder;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.functions.GUIFunctions;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.List;

public class PlayerGUI extends GUI {

    User user;
    User target;

    public PlayerGUI(IAPI api, User user, User target) {
        super(api, user, 0);
        this.user = user;
        this.target = target;
    }

    @Override
    public String parse(String raw, String id, Integer index) {
        return PAPI.parse(user.getOfflinePlayer(), new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
            put("player_name", target.name);
        }}).parseString());
    }

    @Override
    public GUIConfig setConfig() {
        return Main.instance.config.playerGUI;
    }

    @Override
    public InventoryProvider getProvider() {
        return new PlayerGUI(Main.instance, user, target);
    }

    @Override
    public void functionCall(Player player, String function, List<String> args) {
        GUIFunctions.valueOf(function.toUpperCase()).function.execute(this, Main.instance.databaseManager.getUser(player), args);
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean canAddItem(GUIItem guiItem, String id, Integer index) {
        if (id.equals("add_friend") && user.isFriend(target)) {
            return false;
        }
        if (id.equals("remove_friend") && !user.isFriend(target)) {
            return false;
        }
        if (id.equals("inventory") && !user.isFriend(target)) {
            return false;
        }
        if (id.equals("teleport") && !user.isFriend(target)) {
            return false;
        }
        return true;
    }

    @Override
    public void setItems(Player player, InventoryContents inventoryContents) {

    }

    @Override
    public void beforeUpdate(Player player, InventoryContents inventoryContents) {

    }

    @Override
    public void onInventoryClose(InventoryCloseEvent inventoryCloseEvent) {

    }

    @Override
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {

    }

    @Override
    public void onPlayerInventoryClick(InventoryClickEvent inventoryClickEvent) {

    }

    @Override
    public boolean preventClose() {
        return false;
    }

    @Override
    public void changePage(int i) {

    }
}
