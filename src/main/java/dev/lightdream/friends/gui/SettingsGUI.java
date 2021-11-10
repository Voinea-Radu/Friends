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

public class SettingsGUI extends GUI {

    private final User user;

    public SettingsGUI(IAPI api, User user) {
        super(api, user);
        this.user = user;
    }

    @Override
    public String parse(String raw, String id, Integer index) {
        if (id.equals("toggle_friend_requests")) {
            return PAPI.parse(user.getOfflinePlayer(), new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
                put("status", user.friendRequest ?
                        Main.instance.lang.enabled :
                        Main.instance.lang.disabled);
            }}).parseString());
        }
        if (id.equals("toggle_teleports")) {
            return PAPI.parse(user.getOfflinePlayer(), new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
                put("status", user.teleport ?
                        Main.instance.lang.enabled :
                        Main.instance.lang.disabled);
            }}).parseString());
        }
        if (id.equals("toggle_party_invites")) {
            return PAPI.parse(user.getOfflinePlayer(), new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
                put("status", user.partyInvite ?
                        Main.instance.lang.enabled :
                        Main.instance.lang.disabled);
            }}).parseString());
        }
        return PAPI.parse(user.getOfflinePlayer(), raw);
    }

    @Override
    public GUIConfig setConfig() {
        return Main.instance.config.settingsGUI;
    }

    @Override
    public InventoryProvider getProvider() {
        return new SettingsGUI(Main.instance, user);
    }

    @Override
    public void functionCall(Player player, String function, List<String> args) {
        GUIFunctions.valueOf(function.toUpperCase()).function.execute(this, Main.instance.databaseManager.getUser(player), args);
    }

    @Override
    public boolean canAddItem(GUIItem guiItem, String s, Integer integer) {
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
