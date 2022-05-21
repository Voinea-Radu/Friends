package dev.lightdream.friends.commands.friends;

import dev.lightdream.commandmanager.annotation.Command;
import dev.lightdream.friends.Main;

@Command(aliases = {"friends", "friend"})
public class Base extends dev.lightdream.commandmanager.command.Command {
    public Base() {
        super(Main.instance);
    }
}
