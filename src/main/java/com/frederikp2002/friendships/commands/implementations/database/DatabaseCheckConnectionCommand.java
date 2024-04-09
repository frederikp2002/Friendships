package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import org.bukkit.entity.Player;

public class DatabaseCheckConnectionCommand extends Command {

    private final IDatabaseHandler databaseHandler;

    public DatabaseCheckConnectionCommand() {
        super("database.checkConnection");
        this.databaseHandler = super.databaseHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (databaseHandler.isConnected()) {
            player.sendMessage(messageHandler.getMessage("command.database.checkConnection.connected"));
        } else {
            player.sendMessage(messageHandler.getMessage("command.database.checkConnection.disconnected"));
        }
    }

}
