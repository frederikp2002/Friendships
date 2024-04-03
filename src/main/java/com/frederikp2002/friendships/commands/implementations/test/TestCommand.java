package com.frederikp2002.friendships.commands.implementations.test;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.ICommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TestCommand extends Command {
    private final TestNoArgumentsCommand testNoArgumentsCommand;

    public TestCommand() {
        this.testNoArgumentsCommand = new TestNoArgumentsCommand();
        super.registerSubcommand(new TestWithAnArgumentCommand());
        super.registerSubcommand(new TestWithAnotherArgumentCommand());
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length <= 1) {
            testNoArgumentsCommand.execute(player, args);
            return;
        }

        String subcommandKey = args[1].toLowerCase();
        ICommand subcommand = subcommands.get(subcommandKey);
        if (subcommand != null) {
            subcommand.execute(player, Arrays.copyOfRange(args, 2, args.length));
        } else {
            testNoArgumentsCommand.execute(player, args);
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"test", "testcommamnd"};
    }

}
