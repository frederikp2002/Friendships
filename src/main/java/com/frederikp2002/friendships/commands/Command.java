package com.frederikp2002.friendships.commands;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Command implements ICommand {
    protected final Map<String, ICommand> subcommands = new HashMap<>();

    public abstract void execute(Player player, String[] args);

    public String[] getAliases() {
        return new String[0];
    }

    protected void registerSubcommand(ICommand command) {
        for (String alias : command.getAliases()) {
            subcommands.put(alias, command);
        }
    }

    @Override
    public String[] getTabCompleteOptions(Player player, String[] args) {
        // Check if args length is less than 2, return early.
        if (args.length < 2) {
            return new String[0];
        }

        // Providing suggestions for the first level of subcommands.
        if (args.length == 2) {
            return subcommands.keySet().toArray(new String[0]);
        } else {
            // Now, args.length is at least 3.
            ICommand subcommand = subcommands.get(args[1].toLowerCase());
            if (subcommand != null) {
                // Ensure that when delegating, args is appropriately sized.
                if (args.length > 2) {
                    // Only proceed if there are arguments beyond the subcommand.
                    return subcommand.getTabCompleteOptions(player, Arrays.copyOfRange(args, 2, args.length));
                }
            }
        }

        // Default case: no completions available.
        return new String[0];
    }


}
