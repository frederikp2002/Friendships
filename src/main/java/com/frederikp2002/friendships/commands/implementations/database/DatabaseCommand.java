package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DatabaseCommand extends Command {

    private final DatabaseNoArgumentsCommand databaseNoArgsCommand;

    public DatabaseCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.databaseNoArgsCommand = new DatabaseNoArgumentsCommand(messageHandler, configHandler);
        super.registerSubcommand(new DatabaseCheckConnectionCommand(messageHandler, configHandler, databaseHandler));
        super.registerSubcommand(new DatabaseCheckConnectionCommand(messageHandler, configHandler, databaseHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            databaseNoArgsCommand.execute(player, args);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            databaseNoArgsCommand.execute(player, args);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"database", "db"};
    }

}