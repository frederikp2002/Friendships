package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.TabCompletable;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand extends Command implements TabCompletable {

    private final ReloadNoArgumentsCommand reloadNoArgumentsCommand;

    public ReloadCommand() {
        super("reload.main");
        this.reloadNoArgumentsCommand = new ReloadNoArgumentsCommand(super.messageHandler, super.configHandler);
        this.addSubCommand(new ReloadConfigCommand(super.messageHandler, super.configHandler));
    }

    @Override
    public void execute(Player player, String[] args) {
        super.checkForSubcommands(player, args);
        reloadNoArgumentsCommand.execute(player);
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return super.tabComplete(args);
    }

}
