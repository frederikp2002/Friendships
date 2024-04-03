package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.ICommand;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ReloadCommand extends Command {

    ReloadNoArgumentsCommand reloadNoArgsCommand;

    public ReloadCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.reloadNoArgsCommand = new ReloadNoArgumentsCommand(messageHandler, configHandler);
        super.registerSubcommand(new ReloadConfigCommand(messageHandler, configHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            reloadNoArgsCommand.execute(player, args);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            reloadNoArgsCommand.execute(player, args);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"reload", "rl"};
    }

}

