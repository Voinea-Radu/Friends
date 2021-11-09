package dev.lightdream.friends.gui.functions.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.SettingsGUI;
import dev.lightdream.friends.gui.functions.GUIFunction;

import java.util.List;

public class ChangeSetting implements GUIFunction {
    @Override
    public void execute(GUI gui, User user, List<String> args) {
        if (args.size() != 2) {
            return;
        }

        String setting = args.get(0);
        String change = args.get(1);

        switch (setting) {
            case "friend_request":
                user.friendRequest = changeTo(user.friendRequest, change);
                break;
            case "change_setting":
                user.teleport = changeTo(user.teleport, change);
                break;
            case "party_invite":
                user.partyInvite = changeTo(user.partyInvite, change);
                break;
        }
        user.save();
        new SettingsGUI(Main.instance, user).open();
    }

    public boolean changeTo(boolean current, String change) {
        switch (change) {
            case "true":
                return true;
            case "false":
                return false;
            case "toggle":
                return !current;
        }
        return current;
    }
}
