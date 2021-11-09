package dev.lightdream.friends.commands.Party;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;

import java.util.ArrayList;
import java.util.List;

public class Leave extends SubCommand {

    public Leave() {
        super(Main.instance, "join", true, false, "");
    }

    @Override
    public void execute(User u, List<String> args) {
        dev.lightdream.friends.database.User user = (dev.lightdream.friends.database.User) u;
        user.leaveParty();
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }

}
