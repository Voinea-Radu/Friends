package dev.lightdream.friends.commands.Party;

import dev.lightdream.commandmanager.annotation.Command;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Arrays;
import java.util.List;

@Command(aliases = {"remove"}, parent = Base.class, onlyForPlayers = true)
public class Chat extends dev.lightdream.commandmanager.command.Command {
    public Chat() {
        super(Main.instance);
    }

    @Override
    public List<CommandElement> getArgs() {
        return Arrays.asList(
                GenericArguments.text(Text.of("text"), TextSerializers.PLAIN, true)
        );
    }

    @Override
    public void exec(@NotNull Player player, @NotNull CommandContext args) {
        User user = Main.instance.databaseManager.getUser(player);

        String message = args.<String>getOne("text").get();

        user.sendPartyMessage(message);
    }
}
