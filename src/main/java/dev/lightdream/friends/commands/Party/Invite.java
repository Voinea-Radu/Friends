package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.ArrayList;
import java.util.List;

public class Invite extends SubCommand {
    public Invite() {
        super(Main.instance, "invite", true, false, "[user]");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;

        if (args.size() == 0) {
            sendUsage(user);
            return;
        }

        if (!user.hasParty()) {
            user.sendMessage(Main.instance, Main.instance.lang.partyDoesNotExist);
            return;
        }
        dev.lightdream.friends.database.User target = Main.instance.databaseManager.getUser(args.get(0));
        if (target == null) {
            user.sendMessage(Main.instance, Main.instance.lang.invalidUser);
            return;
        }
        user.inviteParty(target);
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }
}
