package dev.lightdream.friends.gui.functions;

import dev.lightdream.friends.gui.functions.functions.*;

@SuppressWarnings("unused")
public enum GUIFunctions {

    REMOVE_FRIEND(new RemoveFriend()),
    NEXT_PAGE(new NextPage()),
    REQUEST_FRIEND(new RequestFriend()),
    OPEN_GUI(new OpenGUI()),
    SHOW_INVENTORY(new ShowInventory()),
    TELEPORT(new Teleport()),
    CHANGE_SETTING(new ChangeSetting()),
    CREATE_PARTY(new CreateParty()),
    DISBAND_PARTY(new DisbandParty()),
    INVITE_PARTY(new InviteParty()),
    LEAVE_PARTY(new LeaveParty()),
    JOIN_PARTY(new JoinParty()),
    BACK_PAGE(new BackPage());

    public GUIFunction function;

    GUIFunctions(GUIFunction function) {
        this.function = function;
    }
}
