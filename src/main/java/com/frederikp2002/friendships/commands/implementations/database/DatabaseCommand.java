package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseCommand implements ICommand {

    private final DatabaseNoArgsCommand databaseNoArgsCommand;
    private final Map<String, ICommand> subcommands = new HashMap<>();

    public DatabaseCommand(IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.databaseNoArgsCommand = new DatabaseNoArgsCommand(messageHandler, configHandler);
        registerSubcommand(new DatabaseCheckConnectionCommand(messageHandler, configHandler, databaseHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            databaseNoArgsCommand.DatabaseNoArgsFound(player);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            databaseNoArgsCommand.DatabaseNoArgsFound(player);
        }
    }

    private void registerSubcommand(ICommand command) {
        for (String alias : command.getAliases()) {
            subcommands.put(alias, command);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"database", "db"};
    }

    @Override
    public String[] getTabCompleteOptions(Player player, String[] args) {
        if (args.length >= 2) {
            if (args.length == 2) {
                return subcommands.keySet().toArray(new String[0]);
            }

            ICommand subcommand = subcommands.get(args[1]);
            if (subcommand != null) {
                return subcommand.getTabCompleteOptions(player, Arrays.copyOfRange(args, 2, args.length));
            }
        }
        return new String[0];
    }

}