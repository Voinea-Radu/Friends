package dev.lightdream.friends.managers;

import dev.lightdream.databasemanager.database.ProgrammaticHikariDatabaseManager;
import dev.lightdream.databasemanager.dto.QueryConstrains;
import dev.lightdream.friends.Main;
import dev.lightdream.friends.database.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class DatabaseManager extends ProgrammaticHikariDatabaseManager {
    public DatabaseManager() {
        super(Main.instance);
    }

    public @NotNull User getUser(@NotNull UUID uuid) {
        User user = get(User.class)
                .query(new QueryConstrains().equals("uuid", uuid))
                .query().stream().findAny().orElse(null);

        if (user != null) {
            return user;
        }

        user = new User(uuid, Sponge.getServer().getPlayer(uuid).get().getName());
        save(user);
        return user;
    }

    public @Nullable User getUser(@NotNull String name) {
        return get(User.class)
                .query(new QueryConstrains().equals("name", name))
                .query().stream().findAny().orElse(null);
    }

    public @NotNull User getUser(@NotNull Player player) {
        return getUser(player.getUniqueId());
    }

    public @Nullable User getUser(int id) {
        return get(User.class)
                .query(new QueryConstrains().equals("id", id))
                .query().stream().findAny().orElse(null);
    }

    @Override
    public void setup() {
        setup(User.class);
    }
}
