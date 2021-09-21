package dev.lightdream.chunkfriends.gui;

import dev.lightdream.api.files.dto.Item;
import dev.lightdream.api.utils.ItemBuilder;
import dev.lightdream.api.utils.MessageBuilder;
import dev.lightdream.api.utils.NbtUtils;
import dev.lightdream.api.utils.Utils;
import dev.lightdream.chunkfriends.Main;
import dev.lightdream.chunkfriends.database.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OnlinePlayersGUI implements GUI {

    private final Main plugin;
    private final User user;
    private final int page;

    public OnlinePlayersGUI(Main plugin, User user, int page) {
        this.plugin = plugin;
        this.user = user;
        this.page = page;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (item == null) {
            return;
        }
        if (item.getType().equals(Material.AIR)) {
            return;
        }

        Object guiProtect = NbtUtils.getNBT(item, "gui_protect");
        Object guiUse = NbtUtils.getNBT(item, "gui_use");

        if (guiProtect != null && (Boolean) guiProtect) {
            event.setCancelled(true);
        }

        if (guiUse != null) {
            switch ((String) guiUse) {
                case "back":
                    player.openInventory(new OnlinePlayersGUI(plugin, this.user, page - 1).getInventory());
                    break;
                case "next":
                    player.openInventory(new OnlinePlayersGUI(plugin, this.user, page + 1).getInventory());
                    break;
                case "player":
                    User friend = Main.instance.getDatabaseManager().getUser(UUID.fromString((String) NbtUtils.getNBT(item, "player")));
                    switch (event.getClick()) {
                        case RIGHT:
                            user.removeFriend(friend);
                            break;
                        case MIDDLE:
                            Bukkit.dispatchCommand(player, (String) new MessageBuilder(plugin.config.muteCommand).addPlaceholders(new HashMap<String, String>() {{
                                put("player", friend.name);
                            }}).parse());
                            break;
                        case LEFT:
                            user.addFriend(friend);
                            break;
                    }
                    break;
                case "open_friends_gui":
                    player.openInventory(new FriendsGUI(plugin, this.user, 0).getInventory());
                    break;
            }
        }

    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {

    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, Utils.color(plugin.friendsGUIConfig.title));
        //Utils.fillInventory(inventory, ItemBuilder.makeItem(plugin.friendsGUIConfig.fillItem), plugin.friendsGUIConfig.fillItemPositions);

        List<Integer> availablePositions = new ArrayList<>();
        List<UUID> players = new ArrayList<>();

        inventory.setItem(plugin.friendsGUIConfig.backItem.slot, NbtUtils.setNBT(NbtUtils.setNBT(ItemBuilder.makeItem(plugin.friendsGUIConfig.backItem), "gui_use", "back"), "gui_protect", true));
        inventory.setItem(plugin.friendsGUIConfig.nextItem.slot, NbtUtils.setNBT(NbtUtils.setNBT(ItemBuilder.makeItem(plugin.friendsGUIConfig.nextItem), "gui_use", "next"), "gui_protect", true));
        inventory.setItem(plugin.friendsGUIConfig.openFriendsGUI.slot, NbtUtils.setNBT(NbtUtils.setNBT(ItemBuilder.makeItem(plugin.friendsGUIConfig.openFriendsGUI), "gui_use", "open_friends_gui"), "gui_protect", true));

        for (int i = 0; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                availablePositions.add(i);
            }
        }

        for (int i = availablePositions.size() * page; i < availablePositions.size() * (page + 1); i++) {
            if (Bukkit.getOnlinePlayers().size() > i) {
                Player onlinePlayer = (Player) Bukkit.getOnlinePlayers().toArray()[i];
                if (onlinePlayer.getUniqueId() != user.uuid) {
                    players.add(onlinePlayer.getUniqueId());
                }
            }
        }

        if (page == 0) {
            inventory.setItem(plugin.friendsGUIConfig.backItem.slot, null);
        }

        if (page == Bukkit.getOnlinePlayers().size() / availablePositions.size()) {
            inventory.setItem(plugin.friendsGUIConfig.nextItem.slot, null);
        }

        Item playerTemplate = plugin.friendsGUIConfig.playerItem;

        for (int i = 0; i < Math.min(availablePositions.size(), players.size()); i++) {
            Item head = playerTemplate.clone();
            UUID friend = players.get(i);

            head.headOwner = Bukkit.getOfflinePlayer(friend).getName();
            head.displayName = parse(head.displayName, head.headOwner);
            head.lore = parse(head.lore, head.headOwner);

            ItemStack item = ItemBuilder.makeItem(head);
            item = NbtUtils.setNBT(item, "gui_protect", true);
            item = NbtUtils.setNBT(item, "gui_use", "player");
            item = NbtUtils.setNBT(item, "player", friend.toString());

            inventory.setItem(availablePositions.get(i), item);
        }

        return inventory;
    }

    private String parse(String raw, String player) {
        String parsed = raw;

        parsed = parsed.replace("%player%", player);

        return parsed;
    }

    private List<String> parse(List<String> raw, String player) {
        List<String> parsed = new ArrayList<>();

        raw.forEach(line -> parsed.add(parse(line, player)));

        return parsed;
    }

}
