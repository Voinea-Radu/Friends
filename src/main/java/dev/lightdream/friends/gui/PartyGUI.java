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

public class PartyGUI extends GUI {

    private final User user;

    public PartyGUI(IAPI api, User user) {
        super(api, user);
        this.user = user;
    }

    @Override
    public String parse(String raw, String id, Integer index) {
        if (id.equals("party_members")) {
            return new MessageBuilder(raw).addPlaceholders(new HashMap<String, String>() {{
                put("player_name", ((User) user.party.users.toArray()[index]).name);
            }}).parseString();
        }

        return raw;
    }

    @Override
    public GUIConfig setConfig() {
        return Main.instance.config.partyGUI;
    }

    @Override
    public InventoryProvider getProvider() {
        return new PartyGUI(Main.instance, user);
    }

    @Override
    public void functionCall(Player player, String function, List<String> args) {
        GUIFunctions.valueOf(function.toUpperCase()).function.execute(this, Main.instance.databaseManager.getUser(player), args);
    }

    @Override
    public boolean canAddItem(GUIItem guiItem, String id, Integer index) {
        if (id.equals("disband_party") && (!user.hasParty() || !user.isPartyOwner())) {
            return false;
        }
        if (id.equals("create_party") && user.hasParty()) {
            return false;
        }
        if (id.equals("invite_party") && !user.hasParty()) {
            return false;
        }
        if (id.equals("party_members")) {
            if (!user.hasParty()) {
                return false;
            }
            return index < user.party.users.size();
        }
        return !id.equals("leave_party") || !user.isPartyOwner();
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
