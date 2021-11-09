package dev.lightdream.friends.gui.functions.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.functions.GUIFunction;

import java.util.List;

public class ShowInventory implements GUIFunction {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void execute(GUI gui, User user, List<String> args) {
        if (args.size() == 0) {
            return;
        }

        User target = Main.instance.databaseManager.getUser(args.get(0));

        if (target == null) {
            return;
        }

        if (!target.isOnline()) {
            return;
        }

        if (!user.isFriend(target)) {
            return;
        }

        user.getPlayer().openInventory(target.getPlayer().getInventory());
        Main.instance.eventManager.invseePlayers.add(user.getPlayer());
    }
}
