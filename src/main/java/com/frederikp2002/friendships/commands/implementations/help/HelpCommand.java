package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.TabCompletable;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand extends Command implements TabCompletable {
    private final HelpNoArgumentsCommand helpNoArgumentsCommand;

    public HelpCommand() {
        super("help.main");
        this.helpNoArgumentsCommand = new HelpNoArgumentsCommand(super.messageHandler, super.configHandler);
        this.addSubCommand(new HelpDatabaseCommand());
        this.addSubCommand(new HelpReloadCommand());
    }

    @Override
    public void execute(Player player, String[] args) {
        super.checkForSubcommands(player, args);
        helpNoArgumentsCommand.execute(player);
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return super.tabComplete(args);
    }
}
