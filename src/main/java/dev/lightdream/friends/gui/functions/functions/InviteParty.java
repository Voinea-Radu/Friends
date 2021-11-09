package dev.lightdream.friends.gui.functions.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.functions.GUIFunction;

import java.util.List;

public class InviteParty implements GUIFunction {
    @Override
    public void execute(GUI gui, User user, List<String> args) {
        if (args.size() != 2) {
            return;
        }

        User target = Main.instance.databaseManager.getUser(args.get(1));

        if (target == null) {
            user.sendMessage(Main.instance, Main.instance.lang.invalidUser);
            return;
        }

        user.inviteParty(target);
    }
}
