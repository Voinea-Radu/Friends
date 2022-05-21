package dev.lightdream.friends.database;

import dev.lightdream.databasemanager.annotations.database.DatabaseField;
import dev.lightdream.databasemanager.annotations.database.DatabaseTable;
import dev.lightdream.databasemanager.dto.entry.impl.IntegerDatabaseEntry;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.dto.Party;
import dev.lightdream.messagebuilder.MessageBuilder;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DatabaseTable(table = "users")
public class User extends IntegerDatabaseEntry {

    @DatabaseField(columnName = "name")
    public String name;
    @DatabaseField(columnName = "uuid")
    public UUID uuid;
    @DatabaseField(columnName = "friends")
    public List<Integer> friends;
    @DatabaseField(columnName = "requests")
    public List<Integer> friendRequests;
    @DatabaseField(columnName = "friend_request")
    public boolean friendRequest;
    @DatabaseField(columnName = "teleport")
    public boolean teleport;
    @DatabaseField(columnName = "party_invite")
    public boolean partyInvite;
    public Party party;

    public User(UUID uuid, String name) {
        super(Main.instance);
        this.name = name;
        this.uuid = uuid;
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.partyInvite = true;
        this.teleport = true;
        this.friendRequest = true;
    }

    public User() {
        super(Main.instance);
    }

    public Player getPlayer() {
        return Sponge.getServer().getPlayer(uuid).get();
    }

    public boolean isFriend(User user) {
        return friends.contains(user.id);
    }

    public int getLimit() {
        int limit = 0;
        for (String permission : Main.instance.config.permissionMap.keySet()) {
            if (getPlayer().hasPermission(permission)) {
                limit = Main.instance.config.permissionMap.get(permission);
                break;
            }
        }
        return limit;
    }

    public void sendMessage(String message) {
        getPlayer().sendMessage(Text.of(message));
    }

    public void sendMessage(MessageBuilder message) {
        getPlayer().sendMessage(Text.of(message.parse()));
    }

    public void requestFriend(User target) {

        if (!target.friendRequest) {
            sendMessage(Main.instance.lang.userDoesNotAllow);
            return;
        }

        if (friends.contains(target.id)) {
            sendMessage(Main.instance.lang.alreadyFriends);
            return;
        }

        if (friendRequests.contains(target.id)) {
            sendMessage(Main.instance.lang.alreadyRequested);
            return;
        }
        if (target.equals(this)) {
            sendMessage(Main.instance.lang.cannotFriendYourself);
            return;
        }
        if (isFriend(target)) {
            sendMessage(Main.instance.lang.alreadyFriends);
            return;
        }

        if (friends.size() >= getLimit()) {
            sendMessage(Main.instance.lang.friendsLimit);
            return;
        }

        this.friendRequests.add(target.id);
        target.sendMessage(new MessageBuilder(Main.instance.lang.friendRequested)
                .parse("player_name", this.name)
        );
        save();
        sendMessage(Main.instance.lang.requested);
    }

    public void addFriend(User friend) {
        if (friends.contains(friend.id)) {
            sendMessage(Main.instance.lang.alreadyFriends);
            return;
        }
        friends.add(friend.id);
        sendMessage(new MessageBuilder(Main.instance.lang.friendAdded)
                .parse("player_name", friend.name)
        );
        save();
    }

    public void removeFriend(User user) {
        friends.remove(user.id);
        save();
        sendMessage(Main.instance.lang.friendRemoved);
    }

    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        this.friends.forEach(friend ->
                friends.add(Main.instance.databaseManager.getUser(friend)));
        return friends;
    }

    public void createParty() {
        if (hasParty()) {
            sendMessage(Main.instance.lang.youAreAlreadyInParty);
            return;
        }
        this.party = new Party(this);
    }

    public void disbandParty() {
        if (!isPartyOwner()) {
            sendMessage(Main.instance.lang.notPartyOwner);
            return;
        }
        party.disband();
        this.party = null;
        sendMessage(Main.instance.lang.partyDisbanded);
    }

    public void joinParty(Party party) {
        this.party = party;
        party.join(this);
    }

    public void leaveParty() {
        if (!hasParty()) {
            sendMessage(Main.instance.lang.notHaveParty);
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
            sendMessage(Main.instance.lang.notHaveParty);
            return;
        }
        party.sendMessage(message);
    }

    public void inviteParty(User target) {
        if (!hasParty()) {
            sendMessage(Main.instance.lang.notHaveParty);
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

    public boolean isOnline(){
        return getPlayer().isOnline();
    }


}
