package dev.lightdream.friends.database;

import dev.lightdream.api.utils.MessageBuilder;
import dev.lightdream.friends.Main;
import dev.lightdream.libs.j256.field.DataType;
import dev.lightdream.libs.j256.field.DatabaseField;

import java.util.*;

public class User extends dev.lightdream.api.databases.User {

    @DatabaseField(columnName = "friends", dataType = DataType.SERIALIZABLE)
    public ArrayList<Integer> friends;
    @DatabaseField(columnName = "requests", dataType = DataType.SERIALIZABLE)
    public HashSet<Integer> requests;

    public User(UUID uuid, String name, String lang) {
        super(Main.instance, uuid, name, lang);
        this.friends = new ArrayList<>();
        this.requests = new HashSet<>();
    }

    public User() {

    }

    public boolean isFriend(User user) {
        return friends.contains(user.id);
    }

    public int getLimit() {
        int limit = 0;
        for (String permission : Main.instance.config.permissionMap.keySet()) {
            //noinspection ConstantConditions
            if (getPlayer().hasPermission(permission)) {
                limit = Main.instance.config.permissionMap.get(permission);
                break;
            }
        }
        return limit;
    }

    public void requestFriend(User target) {
        if (target == this) {
            sendMessage(Main.instance, Main.instance.lang.cannotFriendYourself);
            return;
        }
        if (isFriend(target)) {
            sendMessage(Main.instance, Main.instance.lang.alreadyFriends);
            return;
        }

        if (friends.size() >= getLimit()) {
            sendMessage(Main.instance, Main.instance.lang.friendsLimit);
            return;
        }

        this.requests.add(target.id);
        target.sendMessage(Main.instance, new MessageBuilder(Main.instance.lang.friendRequested).addPlaceholders(new HashMap<String, String>() {{
            put("player_name", name);
        }}));
        save();
        sendMessage(Main.instance, Main.instance.lang.requested);
    }

    public void addFriend(User friend) {
        friends.add(friend.id);
        sendMessage(Main.instance, new MessageBuilder(Main.instance.lang.friendAdded).addPlaceholders(new HashMap<String, String>() {{
            put("player_name", friend.name);
        }}));
        save();
    }

    public void removeFriend(User user) {
        friends.remove(user.id);
        save();
        sendMessage(Main.instance, Main.instance.lang.friendRemoved);
    }

    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        this.friends.forEach(friend -> friends.add(Main.instance.getDatabaseManager().getUser(friend)));
        return friends;
    }

    @Override
    public void save() {
        save(false);
    }
}
