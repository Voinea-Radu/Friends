package dev.lightdream.friends.commands;

import dev.lightdream.api.commands.SubCommand;
import dev.lightdream.api.databases.User;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.gui.MainMenuGUI;

import java.util.ArrayList;
import java.util.List;

public class MainMenuBase extends SubCommand {
    public MainMenuBase() {
        super(Main.instance, "", true, false, "");
    }

    @Override
    public void execute(User user, List<String> list) {
        new MainMenuGUI(Main.instance, user).open();
    }

    @Override
    public List<String> onTabComplete(User user, List<String> list) {
        return new ArrayList<>();
    }
}
