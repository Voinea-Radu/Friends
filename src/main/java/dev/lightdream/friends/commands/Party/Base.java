package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.gui.PartyGUI;

import java.util.Arrays;
import java.util.List;

public class Base extends SubCommand {
    public Base() {
        super(Main.instance, "", true, false, "");
    }

    @Override
    public void execute(User u, List<String> args) {
        new PartyGUI(Main.instance, (dev.lightdream.friends.database.User) u).open();
    }

    @Override
    public List<String> onTabComplete(User user, List<String> args) {
        if (args.size() == 1) {
            return Arrays.asList(
                    "join",
                    "leave",
                    "disband",
                    "chat",
                    "invite"
            );
        }
        return null;
    }
}
