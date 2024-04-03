package com.frederikp2002.friendships.commands.implementations.test;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.ICommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TestWithAnArgumentCommand extends Command {

    public TestWithAnArgumentCommand() {
        super.registerSubcommand(new TestWithAnArgumentWithArgumentCommand());
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length > 0) {
            // Attempt to find and execute a subcommand if additional arguments are present
            ICommand subcommand = subcommands.get(args[0].toLowerCase());
            if (subcommand != null) {
                subcommand.execute(player, Arrays.copyOfRange(args, 1, args.length));
                return;
            }
        }
        // Default action if no further subcommands match or no additional arguments are present
        player.sendMessage("Test command with argument executed!");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"testarg", "testargument"};
    }


    @Override
    public String[] getTabCompleteOptions(Player player, String[] args) {
        if (args.length == 1) {
            // This is the case where the player has typed "/friend test testarg " and is looking for further completions.
            return subcommands.keySet().toArray(new String[0]);
        } else if (args.length > 1) {
            // Deeper nesting: delegate to the next level of subcommands, if there are any.
            ICommand nextSubcommand = subcommands.get(args[1].toLowerCase());
            if (nextSubcommand != null) {
                return nextSubcommand.getTabCompleteOptions(player, Arrays.copyOfRange(args, 2, args.length));
            }
        }
        // Default case: no further completions available
        return new String[0];
    }
}