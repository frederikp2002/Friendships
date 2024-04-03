package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class DatabaseCheckConnectionCommand extends Command {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;
    private final IDatabaseHandler databaseHandler;

    public DatabaseCheckConnectionCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
        this.databaseHandler = databaseHandler;
    }

    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.database.checkConnection.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.database.checkConnection.disabled"));
            return;
        }

        if (databaseHandler.isConnected()) {
            player.sendMessage(messageHandler.getMessage("command.database.checkConnection.connected"));
        } else {
            player.sendMessage(messageHandler.getMessage("command.database.checkConnection.disconnected"));
        }
    }

    public String[] getAliases() {
        return new String[]{"checkConnection", "check"};
    }

}