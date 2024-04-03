package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HelpCommand extends Command {

    HelpNoArgumentsCommand helpNoArgumentsCommand;

    public HelpCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.helpNoArgumentsCommand = new HelpNoArgumentsCommand(messageHandler, configHandler);
        super.registerSubcommand(new HelpReloadCommand(messageHandler, configHandler));
        super.registerSubcommand(new HelpDatabaseCommand(messageHandler, configHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            helpNoArgumentsCommand.execute(player, args);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            helpNoArgumentsCommand.execute(player, args);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"help", "helpme"};
    }

}