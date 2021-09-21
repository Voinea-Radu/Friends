package dev.lightdream.chunkfriends.managers;

import dev.lightdream.chunkfriends.Main;
import dev.lightdream.chunkfriends.database.User;

public class FriendsManager {

    private final Main plugin;

    public FriendsManager(Main plugin) {
        this.plugin = plugin;
    }

    public void addFriend(User user, User friend) {
        if (friend == user) {
            plugin.getMessageManager().sendMessage(user, plugin.lang.cannotFriendYourself);
            return;
        }
        if (user.isFriend(friend)) {
            plugin.getMessageManager().sendMessage(user, plugin.lang.alreadyFriends);
            return;
        }
        int limit = 0;
        for (String permission : plugin.config.permissionMap.keySet()) {
            if (user.getPlayer().hasPermission(permission)) {
                limit = plugin.config.permissionMap.get(permission);
                break;
            }
        }
        if (user.friends.size() >= limit) {
            plugin.getMessageManager().sendMessage(user, plugin.lang.friendsLimit);
            return;
        }
        user.addFriend(friend);
        plugin.getMessageManager().sendMessage(user, plugin.lang.friendAdded);
    }
}

