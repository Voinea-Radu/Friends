package dev.lightdream.friends.database;

import dev.lightdream.api.utils.MessageBuilder;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.dto.Party;
import dev.lightdream.libs.j256.field.DataType;
import dev.lightdream.libs.j256.field.DatabaseField;

import java.util.*;

public class User extends dev.lightdream.api.databases.User {

    @DatabaseField(columnName = "friends", dataType = DataType.SERIALIZABLE)
    public HashSet<Integer> friends;
    @DatabaseField(columnName = "requests", dataType = DataType.SERIALIZABLE)
    public HashSet<Integer> friendRequests;
    @DatabaseField(columnName = "friend_request")
    public boolean friendRequest;
    @DatabaseField(columnName = "teleport")
    public boolean teleport;
    @DatabaseField(columnName = "party_invite")
    public boolean partyInvite;
    public Party party;

    public User(UUID uuid, String name, String lang) {
        super(Main.instance, uuid, name, lang);
        this.friends = new HashSet<>();
        this.friendRequests = new HashSet<>();
        this.partyInvite = true;
        this.teleport = true;
        this.friendRequest = true;
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

        if (!target.friendRequest) {
            sendMessage(Main.instance, Main.instance.lang.userDoesNotAllow);
            return;
        }

        if (friends.contains(target.id)) {
            sendMessage(Main.instance, Main.instance.lang.alreadyFriends);
            return;
        }

        if (friendRequests.contains(target.id)) {
            sendMessage(Main.instance, Main.instance.lang.alreadyRequested);
            return;
        }
        if (target.equals(this)) {
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

        this.friendRequests.add(target.id);
        target.sendMessage(Main.instance, new MessageBuilder(Main.instance.lang.friendRequested).addPlaceholders(new HashMap<String, String>() {{
            put("player_name", name);
        }}));
        save();
        sendMessage(Main.instance, Main.instance.lang.requested);
    }

    public void addFriend(User friend) {
        if (friends.contains(friend.id)) {
            sendMessage(Main.instance, Main.instance.lang.alreadyFriends);
            return;
        }
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
        this.friends.forEach(friend ->
                friends.add(Main.instance.databaseManager.getUser(friend)));
        return friends;
    }

    public void createParty() {
        if (hasParty()) {
            sendMessage(Main.instance, Main.instance.lang.youAreAlreadyInParty);
            return;
        }
        this.party = new Party(this);
    }

    public void disbandParty() {
        if (!isPartyOwner()) {
            sendMessage(Main.instance, Main.instance.lang.notPartyOwner);
            return;
        }
        party.disband();
        this.party = null;
        sendMessage(Main.instance, Main.instance.lang.partyDisbanded);
    }

    public void joinParty(Party party) {
        this.party = party;
        party.join(this);
    }

    public void leaveParty() {
        if (!hasParty()) {
            sendMessage(Main.instance, Main.instance.lang.notHaveParty);
            return;
        }
        party.leave(this);
    }

    public void leaveParty(boolean message) {
        if (message) {
            leaveParty();
            return;
        }
        if (!hasParty()) {
            return;
        }
        party.leave(this);
    }

    public void sendPartyMessage(String message) {
        if (!hasParty()) {
            sendMessage(Main.instance, Main.instance.lang.notHaveParty);
            return;
        }
        party.sendMessage(message);
    }

    public void inviteParty(User target) {
        if (!hasParty()) {
            sendMessage(Main.instance, Main.instance.lang.notHaveParty);
            return;
        }

        party.invite(this, target);
    }

    public boolean hasParty() {
        return party != null;
    }

    public boolean isPartyOwner() {
        if (!hasParty()) {
            return false;
        }
        return party.isOwner(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "friends=" + friends +
                ", friendRequests=" + friendRequests +
                ", friendRequest=" + friendRequest +
                ", teleport=" + teleport +
                ", partyInvite=" + partyInvite +
                ", party=" + party +
                ", id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
