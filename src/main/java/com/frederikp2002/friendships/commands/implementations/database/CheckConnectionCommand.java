package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class CheckConnectionCommand implements ICommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;
    private final IDatabaseHandler databaseHandler;

    public CheckConnectionCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
        this.databaseHandler = databaseHandler;
    }

    @Override
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

}
