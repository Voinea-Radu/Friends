package dev.lightdream.friends.gui;

import dev.lightdream.api.IAPI;
import dev.lightdream.api.dto.GUIConfig;
import dev.lightdream.api.dto.GUIItem;
import dev.lightdream.api.gui.GUI;
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

public class FriendsGUI extends GUI {

    private final User user;
    private final String type;

    public FriendsGUI(IAPI api, User user) {
        super(api, user);
        this.user = user;
        this.type = "open_gui";
    }

    public FriendsGUI(IAPI api, User user, String type) {
        super(api, user);
        this.user = user;
        this.type = type;
    }

    public FriendsGUI(IAPI api, User user, int page) {
        super(api, user, page);
        this.user = user;
        this.type = "open_gui";
    }

    public FriendsGUI(IAPI api, User user, int page, String type) {
        super(api, user, page);
        this.user = user;
        this.type = type;
    }

    @Override
    public String parse(String raw, String id, Integer index) {

        raw = new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
            put("type", type);
        }}).parseString();

        if (id.equals("friend_head")) {
            if (index >= user.friends.size()) {
                return raw;
            }
            User target = Main.instance.databaseManager.getUser((Integer) user.friends.toArray()[index]);
            if (target == null) {
                return raw;
            }
            return new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
                put("player_name", target.name);
            }}).parseString();
        } else {
            return raw;
        }
    }

    @Override
    public GUIConfig setConfig() {
        return Main.instance.config.friendsGUI;
    }

    @Override
    public InventoryProvider getProvider() {
        return new FriendsGUI(api, user, getPage(), type);
    }

    @Override
    public void functionCall(Player player, String function, List<String> args) {
        GUIFunctions.valueOf(function.toUpperCase()).function.execute(this, Main.instance.databaseManager.getUser(player), args);
    }

    @Override
    public boolean canAddItem(GUIItem guiItem, String s, Integer index) {
        return index < user.friends.size();
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
        new FriendsGUI(api, user, i).open();
    }
}
