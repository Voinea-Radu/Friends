package dev.lightdream.friends.commands.friends;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.List;

public class Add extends SubCommand {
    public Add() {
        super(Main.instance, "add", true, false, "[player]]");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;
        if (args.size() == 0) {
            sendUsage(user);
            return;
        }
        dev.lightdream.friends.database.User target = Main.instance.databaseManager.getUser(args.get(0));
        if (target == null) {
            api.getMessageManager().sendMessage(user, Main.instance.lang.invalidUser);
            return;
        }
        user.requestFriend(target);

    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return null;
    }
}
