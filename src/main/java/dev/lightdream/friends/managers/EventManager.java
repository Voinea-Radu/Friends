package dev.lightdream.friends.managers;

import dev.lightdream.friends.Main;
import org.spongepowered.api.Sponge;

public class EventManager {


    public EventManager() {
        Sponge.getEventManager().registerListeners(Main.instance, this);
    }


}
