package dev.lightdream.chunkfriends.commands;

import dev.lightdream.api.IAPI;
import dev.lightdream.api.commands.Command;
import dev.lightdream.chunkfriends.Main;
import dev.lightdream.chunkfriends.database.User;
import dev.lightdream.chunkfriends.gui.FriendsGUI;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class BaseCommand extends Command {
    public BaseCommand(@NotNull IAPI api) {
        super(api, Collections.singletonList(""), "", "", true, false, "");
    }

    @Override
    public void execute(CommandSender commandSender, List<String> args) {
        User user = Main.instance.getDatabaseManager().getUser(commandSender);
        if (args.size() == 0) {
            user.getPlayer().openInventory(new FriendsGUI(Main.instance, user, 0).getInventory());
            return;
        }
        if (args.size() == 2) {
            User target = Main.instance.getDatabaseManager().getUser(args.get(1));

            switch (args.get(0)) {
                case "add":
                    if (target == null) {
                        api.getMessageManager().sendMessage(user, Main.instance.lang.invalidUser);
                        return;
                    }

                    Main.instance.friendsManager.addFriend(user, target);
                    break;
                case "remove":
                    if (target == null) {
                        api.getMessageManager().sendMessage(user, Main.instance.lang.invalidUser);
                        return;
                    }
                    user.removeFriend(target);
                    break;
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, List<String> list) {
        return null;
    }
}
