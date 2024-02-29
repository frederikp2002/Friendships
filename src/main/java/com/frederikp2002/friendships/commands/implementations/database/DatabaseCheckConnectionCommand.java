package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class DatabaseCheckConnectionCommand implements ICommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;
    private final IDatabaseHandler databaseHandler;

    public DatabaseCheckConnectionCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
        this.databaseHandler = databaseHandler;
    }

    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.database.checkconnection.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.database.checkconnection.disabled"));
            return;
        }

        if (databaseHandler.isConnected()) {
            player.sendMessage(messageHandler.getMessage("command.database.checkconnection.connected"));
        } else {
            player.sendMessage(messageHandler.getMessage("command.database.checkconnection.disconnected"));
        }
    }

    public String[] getAliases() {
        return new String[]{"checkconnection", "check"};
    }

    public String[] getTabCompleteOptions(Player player, String[] args) {
        return new String[0];
    }

}
