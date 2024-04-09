package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.TabCompletable;
import org.bukkit.entity.Player;

import java.util.List;

public class DatabaseCommand extends Command implements TabCompletable {

    private final DatabaseNoArgumentsCommand databaseNoArgsCommand;

    public DatabaseCommand() {
        super("database.main");
        this.addSubCommand(new DatabaseCheckConnectionCommand());
        this.databaseNoArgsCommand = new DatabaseNoArgumentsCommand(super.messageHandler, super.configHandler);
    }

    @Override
    public void execute(Player player, String[] args) {
        super.checkForSubcommands(player, args);
        databaseNoArgsCommand.execute(player);
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return super.tabComplete(args);
    }



}
