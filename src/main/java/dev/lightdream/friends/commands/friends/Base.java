package dev.lightdream.friends.commands.friends;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.FriendsGUI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Base extends SubCommand {
    public Base() {
        super(Main.instance, Collections.singletonList(""), true, false, "add/accept/remove [player]");
    }

    @Override
    public void execute(dev.lightdream.api.databases.User u, List<String> args) {
        new FriendsGUI(Main.instance, (User) u, 0).open();
    }

    @Override
    public List<String> onTabComplete(dev.lightdream.api.databases.User user, List<String> args) {
        if (args.size() == 1) {
            return Arrays.asList(
                    "add",
                    "accept",
                    "remove"
            );
        }
        return null;
    }
}
