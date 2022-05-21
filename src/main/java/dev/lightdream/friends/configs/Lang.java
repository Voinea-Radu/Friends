package dev.lightdream.friends.configs;

import dev.lightdream.commandmanager.dto.CommandLang;
import dev.lightdream.messagebuilder.MessageBuilder;

public class Lang extends CommandLang {

    public MessageBuilder cannotFriendYourself = new MessageBuilder("You cannot friend yourself");
    public MessageBuilder alreadyFriends = new MessageBuilder("You are already friends with this user");
    public MessageBuilder friendsLimit = new MessageBuilder("You have reached the friends limit");
    public MessageBuilder friendAdded = new MessageBuilder("You are now friend with %player_name%");
    public MessageBuilder friendRemoved = new MessageBuilder("Friend removed");
    public MessageBuilder friendRequested = new MessageBuilder("%player_name% has requested to be your friend. If you want to accept please use the command /friends accept %player_name%");
    public MessageBuilder notRequested = new MessageBuilder("This user has not requested to be friend with yuu");
    public MessageBuilder requested = new MessageBuilder("Request has been sent");
    public MessageBuilder alreadyRequested = new MessageBuilder("You have already requested friendship of this user");
    public MessageBuilder notHaveParty = new MessageBuilder("You do not have a party");
    public MessageBuilder enabled = new MessageBuilder("enabled");
    public MessageBuilder disabled = new MessageBuilder("disabled");
    public MessageBuilder userDoesNotAllow = new MessageBuilder("The user does not allow this");
    public MessageBuilder invited = new MessageBuilder("Invite successfully sent");
    public MessageBuilder partyInvite = new MessageBuilder("You have been invited into a party. To join it use /party join %player_name%");
    public MessageBuilder partyDoesNotExist = new MessageBuilder("This user does not seem to own a party");
    public MessageBuilder alreadyInvited = new MessageBuilder("This user has already been invited");
    public MessageBuilder alreadyInParty = new MessageBuilder("This user is already in party");
    public MessageBuilder leftParty = new MessageBuilder("%player_name% has left the party");
    public MessageBuilder joinedParty = new MessageBuilder("%player_name% has joined the party");
    public MessageBuilder youAreAlreadyInParty = new MessageBuilder("You are already in this party");
    public MessageBuilder partyCreated = new MessageBuilder("Party created");
    public MessageBuilder theOwner = new MessageBuilder("You are the owner of the party. You can not leave it. You can try disbanding it.");
    public MessageBuilder notPartyOwner = new MessageBuilder("You are not the party owner");
    public MessageBuilder partyDisbanded = new MessageBuilder("Party disbanded");
    public MessageBuilder notInvited = new MessageBuilder("You have not been invited");
    public MessageBuilder invalidUser = new MessageBuilder("Invalid user");


}
