package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.gui.PartyGUI;

import java.util.ArrayList;
import java.util.List;

public class Disband extends SubCommand {
    public Disband() {
        super(Main.instance, "disband", true, false, "");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;
        user.disbandParty();
        new PartyGUI(Main.instance, user).open();
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }
}
