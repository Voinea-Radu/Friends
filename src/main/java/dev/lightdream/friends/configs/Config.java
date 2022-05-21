package dev.lightdream.friends.configs;

import java.util.HashMap;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class Config {

    public boolean debug = true;
    public HashMap<String, Integer> permissionMap = new HashMap<String, Integer>() {{
        put("friends.200", 200);
        put("friends.100", 100);
    }};

}
