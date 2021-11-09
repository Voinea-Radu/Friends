package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.ArrayList;
import java.util.List;

public class Join extends SubCommand {

    public Join() {
        super(Main.instance, "join", true, false, "[owner]");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;

        if (args.size() == 0) {
            sendUsage(user);
            return;
        }
        dev.lightdream.friends.database.User target = Main.instance.databaseManager.getUser(args.get(1));
        if (target == null) {
            user.sendMessage(Main.instance, Main.instance.lang.invalidUser);
            return;
        }
        if (!target.hasParty()) {
            user.sendMessage(Main.instance, Main.instance.lang.partyDoesNotExist);
            return;
        }
        user.joinParty(target.party);
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }

}
