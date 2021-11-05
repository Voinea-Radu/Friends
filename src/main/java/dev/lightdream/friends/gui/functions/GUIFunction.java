package dev.lightdream.friends.gui.functions;

import dev.lightdream.api.gui.GUI;
import dev.lightdream.friends.database.User;

import java.util.List;

public interface GUIFunction {

    void execute(GUI gui, User user, List<String> args);

}
