package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class DatabaseCommand implements ICommand {

    private final DatabaseNoArgsCommand databaseNoArgsCommand;
    private final DatabaseCheckConnectionCommand databaseCheckConnectionCommand;

    public DatabaseCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.databaseNoArgsCommand = new DatabaseNoArgsCommand(messageHandler, configHandler);
        this.databaseCheckConnectionCommand = new DatabaseCheckConnectionCommand(messageHandler, configHandler, databaseHandler);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 1) {
            databaseNoArgsCommand.DatabaseNoArgsFound(player);
            return;
        }

        if (args[1].equalsIgnoreCase("check")) {
            databaseCheckConnectionCommand.DatabaseCheckConnection(player);
            return;
        }

        databaseNoArgsCommand.DatabaseNoArgsFound(player);

    }

}

