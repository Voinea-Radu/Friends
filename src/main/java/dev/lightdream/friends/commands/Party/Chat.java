package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.ArrayList;
import java.util.List;

public class Chat extends SubCommand {
    public Chat() {
        super(Main.instance, "chat", true, false, "[message]");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;

        if (args.size() == 0) {
            sendUsage(user);
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg);
        }

        user.sendPartyMessage(message.toString());
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }
}
