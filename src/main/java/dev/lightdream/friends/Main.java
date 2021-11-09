package dev.lightdream.friends;

import dev.lightdream.api.API;
import dev.lightdream.api.LightDreamPlugin;
import dev.lightdream.api.configs.SQLConfig;
import dev.lightdream.api.databases.User;
import dev.lightdream.api.managers.CommandManager;
import dev.lightdream.api.managers.MessageManager;
import dev.lightdream.api.managers.database.IDatabaseManagerImpl;
import dev.lightdream.friends.commands.MainMenuBase;
import dev.lightdream.friends.commands.Party.*;
import dev.lightdream.friends.commands.friends.Accept;
import dev.lightdream.friends.commands.friends.Add;
import dev.lightdream.friends.commands.friends.Base;
import dev.lightdream.friends.commands.friends.Remove;
import dev.lightdream.friends.configs.Config;
import dev.lightdream.friends.configs.Lang;
import dev.lightdream.friends.managers.DatabaseManager;
import dev.lightdream.friends.managers.EventManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public final class Main extends LightDreamPlugin {


    public static Main instance;

    //Settings
    public Config config;
    public Lang lang;

    //Managers
    public DatabaseManager databaseManager;
    public EventManager eventManager;


    @Override
    public void onEnable() {
        instance = this;
        init("Friends", "friends", "1.0");
        databaseManager = new DatabaseManager(this);
        this.eventManager = new EventManager(this);
        //noinspection ArraysAsListWithZeroOrOneArgument
        new CommandManager(Main.instance, "mainmenu", Arrays.asList(
                new MainMenuBase()
        ));
        new CommandManager(Main.instance, "party", Arrays.asList(
                new dev.lightdream.friends.commands.Party.Base(),
                new Chat(),
                new Disband(),
                new Invite(),
                new Join(),
                new Leave()
        ));
    }


    @Override
    public @NotNull String parsePapi(OfflinePlayer offlinePlayer, String s) {
        return "";
    }

    @Override
    public void loadConfigs() {
        sqlConfig = fileManager.load(SQLConfig.class);
        config = fileManager.load(Config.class);
        baseConfig = config;
        lang = fileManager.load(Lang.class, fileManager.getFile(baseConfig.baseLang));
        baseLang = lang;
    }

    @Override
    public void disable() {

    }

    @Override
    public void registerFileManagerModules() {

    }

    @Override
    public void registerUser(Player player) {
        databaseManager.getUser(player);
    }

    @Override
    public void loadBaseCommands() {
        baseSubCommands.add(new Base());
        baseSubCommands.add(new Add());
        baseSubCommands.add(new Accept());
        baseSubCommands.add(new Remove());
    }

    @Override
    public MessageManager instantiateMessageManager() {
        return new MessageManager(this, Main.class);
    }

    @Override
    public void registerLangManager() {
        API.instance.langManager.register(Main.class, getLangs());
    }

    @Override
    public HashMap<String, Object> getLangs() {
        HashMap<String, Object> langs = new HashMap<>();

        baseConfig.langs.forEach(lang -> {
            Lang l = fileManager.load(Lang.class, fileManager.getFile(lang));
            langs.put(lang, l);
        });

        return langs;
    }


    @Override
    public IDatabaseManagerImpl getDatabaseManager() {
        return databaseManager;
    }

    @Override
    public void setLang(Player player, String s) {
        User user = databaseManager.getUser(player);
        user.setLang(s);
        databaseManager.save(user);
    }


}
