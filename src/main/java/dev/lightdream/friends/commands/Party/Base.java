package dev.lightdream.friends.commands.Party;

import dev.lightdream.commandmanager.annotation.Command;
import dev.lightdream.friends.Main;

@Command(aliases = {"party"})
public class Base extends dev.lightdream.commandmanager.command.Command {
    public Base() {
        super(Main.instance);
    }
}
