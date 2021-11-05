package dev.lightdream.friends.gui.functions.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.database.User;
import dev.lightdream.friends.gui.functions.GUIFunction;

import java.util.List;

public class BackPage implements GUIFunction {
    @Override
    public void execute(GUI gui, User user, List<String> args) {
        gui.backPage();
    }
}
