package dev.lightdream.friends.commands.friends;

import dev.lightdream.commandmanager.annotation.Command;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.List;

@Command(aliases = {"accept"}, parent = Base.class, onlyForPlayers = true)
public class Accept extends dev.lightdream.commandmanager.command.Command {

    public Accept() {
        super(Main.instance);
    }

    @Override
    public List<CommandElement> getArgs() {
        return Arrays.asList(
                GenericArguments.player(Text.of("player"))
        );
    }

    @Override
    public void exec(@NotNull Player player, @NotNull CommandContext args) {
        User user = Main.instance.databaseManager.getUser(player);

        Player targetPlayer = args.<Player>getOne("player").get();

        User target = Main.instance.databaseManager.getUser(targetPlayer);
        if (!target.friendRequests.contains(user.getID())) {
            user.sendMessage(Main.instance.lang.notRequested);
            return;
        }
        target.addFriend(user);
        user.addFriend(target);
    }

}
