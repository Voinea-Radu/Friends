package dev.lightdream.friends.configs;

import dev.lightdream.api.dto.GUIConfig;
import dev.lightdream.api.dto.GUIItem;
import dev.lightdream.api.dto.Item;
import dev.lightdream.api.dto.XMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class Config extends dev.lightdream.api.configs.Config {

    public HashMap<String, Integer> permissionMap = new HashMap<String, Integer>() {{
        put("friends.200", 200);
        put("friends.100", 100);
    }};

    public GUIConfig friendsGUI = new GUIConfig(
            "friends_gui",
            "CHEST",
            "Friends",
            6,
            new Item(XMaterial.AIR),
            new HashMap<String, GUIItem>() {{
                put("friend_head", new GUIItem(
                        new Item(XMaterial.PLAYER_HEAD, 1, "%player_name%", "%player_name%", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("%type%", Arrays.asList(
                                    "player_gui",
                                    "%player_name%"
                            ));
                        }}),
                        Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53)
                ));
                put("back", new GUIItem(
                        new Item(XMaterial.ARROW, 45, 1, "Back", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("request_friend", "");
                        }})
                ));
                put("next", new GUIItem(
                        new Item(XMaterial.ARROW, 53, 1, "Next", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("next_page", "");
                        }})
                ));
            }},
            false
    );

    public GUIConfig playerGUI = new GUIConfig(
            "player_gui",
            "CHEST",
            "Profile",
            6,
            new Item(XMaterial.AIR),
            new HashMap<String, GUIItem>() {{
                put("add_friend", new GUIItem(
                        new Item(XMaterial.GREEN_WOOL, 0, 1, "Send Friend Request", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("request_friend", "%player_name%");
                        }})
                ));
                put("remove_friend", new GUIItem(
                        new Item(XMaterial.RED_WOOL, 0, 1, "Remove Friend", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("remove_friend", "%player_name%");
                        }})
                ));
                put("inventory", new GUIItem(
                        new Item(XMaterial.CHEST, 1, 1, "Inventory", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("show_inventory", "%player_name%");
                        }})
                ));
                put("profile", new GUIItem(
                        new Item(XMaterial.PLAYER_HEAD, 2, 1, "%player_name%", "player_name%", Arrays.asList(
                                "Status: %papi-placeholder-here%",
                                "Rank: %papi-placeholder-here"
                        ))
                ));
                put("teleport", new GUIItem(
                        new Item(XMaterial.BARRIER, 3, 1, "Teleport", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("teleport", "%player_name%");
                        }})
                ));
                put("join_party", new GUIItem(
                        new Item(XMaterial.CAKE, 4, 1, "Join Party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("join_party", "%player_name%");
                        }})));
            }},
            false
    );

    public GUIConfig mainMenuGUI = new GUIConfig(
            "main_menu_gui",
            "CHEST",
            "Main Menu",
            6,
            new Item(XMaterial.AIR),
            new HashMap<String, GUIItem>() {{
                put("friends", new GUIItem(
                        new Item(XMaterial.PLAYER_HEAD, 0, 1, "Friends", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("open_gui", Arrays.asList(
                                    "friends_gui"
                            ));
                        }})
                ));
                put("settings", new GUIItem(
                        new Item(XMaterial.COMMAND_BLOCK, 1, 1, "Settings", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("open_gui", Arrays.asList(
                                    "settings"
                            ));
                        }})
                ));
                put("party", new GUIItem(
                        new Item(XMaterial.CAKE, 2, 1, "Party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("open_gui", Arrays.asList(
                                    "party"
                            ));
                        }})
                ));
            }},
            false
    );

    public GUIConfig settingsGUI = new GUIConfig(
            "settings_gui",
            "CHEST",
            "settings",
            6,
            new Item(XMaterial.AIR),
            new HashMap<String, GUIItem>() {{
                put("toggle_friend_requests", new GUIItem(
                        new Item(XMaterial.STONE, 0, 1, "Toggle friend requests", Arrays.asList(
                                "%status%"
                        )),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("change_setting", Arrays.asList(
                                    "friend_request",
                                    "toggle"
                            ));
                        }})
                ));
                put("toggle_teleports", new GUIItem(
                        new Item(XMaterial.STONE, 1, 1, "Toggle teleports", Arrays.asList(
                                "%status%"
                        )),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("change_setting", Arrays.asList(
                                    "teleport",
                                    "toggle"
                            ));
                        }})
                ));
                put("toggle_party_invites", new GUIItem(
                        new Item(XMaterial.STONE, 2, 1, "Toggle party invites", Arrays.asList(
                                "%status%"
                        )),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("change_setting", Arrays.asList(
                                    "party_invite",
                                    "toggle"
                            ));
                        }})
                ));
            }},
            false
    );

    public GUIConfig partyGUI = new GUIConfig(
            "party_gui",
            "CHEST",
            "Options",
            6,
            new Item(XMaterial.AIR),
            new HashMap<String, GUIItem>() {{
                put("party_members", new GUIItem(
                        new Item(XMaterial.PLAYER_HEAD, 1, "%player_name%", "%player_name%", Arrays.asList(
                                "Click for details"
                        )),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("open_gui", Arrays.asList(
                                    "player_gui",
                                    "%player_name%"
                            ));
                        }}),
                        Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53)

                ));
                put("disband_party", new GUIItem(
                        new Item(XMaterial.RED_WOOL, 0, 1, "Disband party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("disband_party", "");
                        }})
                ));
                put("create_party", new GUIItem(
                        new Item(XMaterial.GREEN_WOOL, 0, 1, "Create party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("create_party", "");
                        }})
                ));
                put("leave_party", new GUIItem(
                        new Item(XMaterial.RED_WOOL, 0, 1, "Leave party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("leave_party", "");
                        }})
                ));
                put("invite_party", new GUIItem(
                        new Item(XMaterial.GREEN_WOOL, 0, 2, "Invite to party", new ArrayList<>()),
                        new GUIItem.GUIItemArgs(new HashMap<String, Object>() {{
                            put("open_gui", Arrays.asList(
                                    "friends_gui",
                                    "invite_party"
                            ));
                        }})
                ));
            }},
            false
    );

}
