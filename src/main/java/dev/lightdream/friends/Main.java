package dev.lightdream.friends;

import dev.lightdream.commandmanager.CommandMain;
import dev.lightdream.commandmanager.dto.CommandLang;
import dev.lightdream.commandmanager.manager.CommandManager;
import dev.lightdream.databasemanager.DatabaseMain;
import dev.lightdream.databasemanager.database.IDatabaseManager;
import dev.lightdream.databasemanager.dto.DriverConfig;
import dev.lightdream.databasemanager.dto.SQLConfig;
import dev.lightdream.filemanager.FileManager;
import dev.lightdream.filemanager.FileManagerMain;
import dev.lightdream.friends.configs.Config;
import dev.lightdream.friends.configs.Lang;
import dev.lightdream.friends.managers.DatabaseManager;
import dev.lightdream.friends.managers.EventManager;
import dev.lightdream.logger.LoggableMain;
import dev.lightdream.logger.Logger;
import dev.lightdream.messagebuilder.MessageBuilderManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;

@Plugin(
        id = "friends",
        name = "Friends",
        description = "Friends for PokeNinjas",
        url = "https://lightdream.dev",
        authors = {
                "LightDream"
        }
)
public final class Main implements CommandMain, DatabaseMain, FileManagerMain, LoggableMain {


    public static Main instance;

    // Config
    public Config config;
    public Lang lang;
    public SQLConfig sqlConfig;
    public DriverConfig driverConfig;


    // Managers
    public DatabaseManager databaseManager;
    public EventManager eventManager;
    public CommandManager commandManager;
    public FileManager fileManager;


    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Logger.init(this);
        instance = this;
        this.fileManager = new FileManager(this);
        MessageBuilderManager.init(fileManager);
        loadConfigs();

        this.databaseManager = new DatabaseManager();
        this.eventManager = new EventManager();
        commandManager = new CommandManager(Main.instance);

        Logger.good("Friends (by LightDream) has been loaded!");
    }

    @Override
    public CommandLang getLang() {
        return null;
    }

    public void loadConfigs() {
        sqlConfig = fileManager.load(SQLConfig.class);
        config = fileManager.load(Config.class);
        driverConfig = fileManager.load(DriverConfig.class);
        sqlConfig = fileManager.load(SQLConfig.class);
        lang = fileManager.load(Lang.class);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public String getPackageName() {
        return "dev.lightdream.friends";
    }

    @Override
    public File getDataFolder() {
        return new File(System.getProperty("user.dir") + "/config/Friends");
    }


    @Override
    public SQLConfig getSqlConfig() {
        return sqlConfig;
    }

    @Override
    public DriverConfig getDriverConfig() {
        return driverConfig;
    }

    @Override
    public IDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    @Override
    public boolean debug() {
        return config.debug;
    }

    @Override
    public void log(String s) {
        System.out.println(s);
    }
}
