package dev.lightdream.friends.commands;

import dev.lightdream.api.IAPI;
import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.FriendsGUI;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseCommand extends SubCommand {
    public BaseCommand(@NotNull IAPI api) {
        super(api, Collections.singletonList(""), "", "", true, false, "");
    }

    @Override
    public void execute(dev.lightdream.api.databases.User u, List<String> args) {
        User user = (User) u;
        if (args.size() == 0) {
            new FriendsGUI(Main.instance, user, 0).open();
            return;
        }
        if (args.size() == 2) {
            User target = Main.instance.databaseManager.getUser(args.get(1));
            if (target == null) {
                api.getMessageManager().sendMessage(user, Main.instance.lang.invalidUser);
                return;
            }
            switch (args.get(0)) {
                case "add":
                    user.requestFriend(target);
                    break;
                case "accept":
                    if(!target.requests.contains(user.getID())){
                        user.sendMessage(Main.instance, Main.instance.lang.notRequested);
                        return;
                    }
                    target.addFriend(user);
                    user.addFriend(target);
                    break;
                case "remove":
                    user.removeFriend(target);
                    break;
            }
        }

    }

    @Override
    public List<String> onTabComplete(dev.lightdream.api.databases.User user, List<String> list) {
        return new ArrayList<>();
    }
}
