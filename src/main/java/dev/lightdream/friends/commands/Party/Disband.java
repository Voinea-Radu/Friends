package dev.lightdream.friends.commands.Party;

import dev.lightdream.commandmanager.annotation.Command;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

@Command(aliases = {"disband"}, parent = Base.class, onlyForPlayers = true)
public class Disband extends dev.lightdream.commandmanager.command.Command {
    public Disband() {
        super(Main.instance);
    }

    @Override
    public void exec(@NotNull Player player, @NotNull CommandContext args) {
        User user = Main.instance.databaseManager.getUser(player);
        user.disbandParty();
    }
}
