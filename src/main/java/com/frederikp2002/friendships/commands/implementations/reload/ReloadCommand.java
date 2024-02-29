package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.ICommand;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReloadCommand implements ICommand {

    private final ReloadNoArgsCommand reloadNoArgsCommand;
    private final Map<String, ICommand> subcommands = new HashMap<>();

    public ReloadCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.reloadNoArgsCommand = new ReloadNoArgsCommand(messageHandler, configHandler);
        registerSubcommand(new ReloadConfigCommand(messageHandler, configHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            reloadNoArgsCommand.ReloadNoArgsFound(player);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            reloadNoArgsCommand.ReloadNoArgsFound(player);
        }
    }

    private void registerSubcommand(ICommand command) {
        for (String alias : command.getAliases()) {
            subcommands.put(alias, command);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"reload", "rl"};
    }


    @Override
    public String[] getTabCompleteOptions(Player player, String[] args) {
        if (args.length >= 2) {
            if (args.length == 2) {
                return subcommands.keySet().toArray(new String[0]);
            }

            // Now, args.length must be > 2 to reach this point
            ICommand subcommand = subcommands.get(args[1]);
            if (subcommand != null) {
                return subcommand.getTabCompleteOptions(player, Arrays.copyOfRange(args, 2, args.length));
            }
        }
        return new String[0];
    }

}