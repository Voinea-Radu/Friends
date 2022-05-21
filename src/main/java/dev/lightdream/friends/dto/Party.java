package dev.lightdream.friends.dto;

import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.messagebuilder.MessageBuilder;

import java.util.HashSet;

public class Party {

    public User owner;
    public HashSet<User> users;
    public HashSet<User> invites;

    public Party(User user) {
        this.owner = user;
        this.users = new HashSet<>();
        this.invites = new HashSet<>();

        users.add(user);

        owner.sendMessage(Main.instance.lang.partyCreated);
    }

    public void join(User user) {
        if (users.contains(user)) {
            user.sendMessage(Main.instance.lang.youAreAlreadyInParty);
            return;
        }
        if (invites.contains(user)) {
            boolean ok = false;
            for (User u : this.users) {
                if (u.isFriend(user)) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                user.sendMessage(Main.instance.lang.notInvited);
                return;
            }
        }
        user.leaveParty(false);
        sendMessage(new MessageBuilder(Main.instance.lang.joinedParty)
                .parse("player_name", user.name)
        );
        users.add(user);
        invites.remove(user);
    }

    public void leave(User user) {
        if (user.isPartyOwner()) {
            user.sendMessage(Main.instance.lang.theOwner);
            return;
        }
        sendMessage(new MessageBuilder(Main.instance.lang.leftParty)
                .parse("player_name", user.name)
        );
        users.remove(user);
        user.party = null;
        user.save();
    }

    public void sendMessage(String message) {
        users.forEach(user -> {
            if (!user.isOnline()) {
                return;
            }
            user.sendMessage(message);
        });
    }

    public void sendMessage(MessageBuilder message) {
        users.forEach(user -> {
            if (!user.isOnline()) {
                return;
            }
            user.sendMessage(message);
        });
    }

    public void invite(User user, User target) {
        user.sendMessage(Main.instance.lang.invited);
        if (invites.contains(target)) {
            user.sendMessage(Main.instance.lang.alreadyInvited);
            return;
        }
        if (users.contains(target) || target.hasParty()) {
            user.sendMessage(Main.instance.lang.alreadyInParty);
        }
        if (target.isOnline()) {
            target.sendMessage(new MessageBuilder(Main.instance.lang.partyInvite)
                    .parse("player_name", owner.name)
            );
        }
        invites.add(target);
    }

    public boolean isOwner(User user) {
        return user.equals(owner);
    }

    public void disband() {
        this.users.remove(owner);
        this.users.forEach(User::leaveParty);
        this.invites = new HashSet<>();
        this.users = new HashSet<>();
        this.owner = null;
    }

}
