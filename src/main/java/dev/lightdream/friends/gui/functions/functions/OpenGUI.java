package dev.lightdream.friends.gui.functions.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.FriendsGUI;
import dev.lightdream.friends.gui.PartyGUI;
import dev.lightdream.friends.gui.PlayerGUI;
import dev.lightdream.friends.gui.SettingsGUI;
import dev.lightdream.friends.gui.functions.GUIFunction;

import java.util.List;

public class OpenGUI implements GUIFunction {
    @Override
    public void execute(GUI gui, User user, List<String> args) {
        if (args.size() == 0) {
            return;
        }

        String guiName = args.get(0);

        switch (guiName) {
            case "player_gui":
                if (args.size() != 2) {
                    return;
                }
                User target = Main.instance.databaseManager.getUser(args.get(1));

                if (target == null) {
                    return;
                }

                new PlayerGUI(Main.instance, user, target).open();
                break;
            case "settings":
                new SettingsGUI(Main.instance, user).open();
                break;
            case "party":
                new PartyGUI(Main.instance, user).open();
                break;
            case "friends_gui":
                if (args.size() == 2
                ) {
                    String types = args.get(1);
                    new FriendsGUI(Main.instance, user, types).open();
                    break;
                }
                new FriendsGUI(Main.instance, user).open();
                break;
        }
    }
}
