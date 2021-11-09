package dev.lightdream.friends.commands.friends;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.List;

public class Accept extends SubCommand {

    public Accept() {
        super(Main.instance, "accept", true, false, "[player]]");
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
            api.getMessageManager().sendMessage(user, Main.instance.lang.invalidUser);
            return;
        }
        if (!target.friendRequests.contains(user.getID())) {
            user.sendMessage(Main.instance, Main.instance.lang.notRequested);
            return;
        }
        target.addFriend(user);
        user.addFriend(target);
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return null;
    }

}
