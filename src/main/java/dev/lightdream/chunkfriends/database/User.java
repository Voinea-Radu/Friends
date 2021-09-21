package dev.lightdream.chunkfriends.database;

import dev.lightdream.chunkfriends.Main;
import dev.lightdream.libs.j256.field.DataType;
import dev.lightdream.libs.j256.field.DatabaseField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class User extends dev.lightdream.api.databases.User {

    @DatabaseField(columnName = "friends", dataType = DataType.SERIALIZABLE)
    public HashSet<Integer> friends;

    public User(UUID uuid, String name, String lang) {
        super(uuid, name, lang);
        this.friends = new HashSet<>();
    }

    public boolean isFriend(User user){
        return friends.contains(user.id);
    }

    public void addFriend(User user){
        friends.add(user.id);
        Main.instance.getDatabaseManager().save(this);
    }

    public void removeFriend(User user){
        friends.remove(user.id);
        Main.instance.getDatabaseManager().save(this);
    }

    public List<User> getFriends(){
        List<User> friends = new ArrayList<>();
        this.friends.forEach(friend->friends.add(Main.instance.getDatabaseManager().getUser(friend)));
        return friends;
    }



}
