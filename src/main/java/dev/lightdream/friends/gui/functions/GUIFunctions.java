package dev.lightdream.friends.gui.functions;

import dev.lightdream.friends.gui.functions.functions.BackPage;
import dev.lightdream.friends.gui.functions.functions.NextPage;
import dev.lightdream.friends.gui.functions.functions.RemoveFriend;
import dev.lightdream.friends.gui.functions.functions.RequestFriend;

@SuppressWarnings("unused")
public enum GUIFunctions {

    REMOVE_FRIEND(new RemoveFriend()),
    NEXT_PAGE(new NextPage()),
    REQUEST_FRIEND(new RequestFriend()),
    BACK_PAGE(new BackPage());

    public GUIFunction function;

    GUIFunctions(GUIFunction function) {
        this.function = function;
    }
}
