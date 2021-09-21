package dev.lightdream.chunkfriends.files.config;

import java.util.HashMap;

public class Config extends dev.lightdream.api.files.config.Config {

    public HashMap<String, Integer> permissionMap = new HashMap<String, Integer>(){{
        put("friends.200", 200);
        put("friends.100", 100);
    }};

    public String muteCommand=  "ignore %player%";

}
