package com.frederikp2002.friendships.handlers.implementations.commands.executors;

import com.frederikp2002.friendships.commands.Command;
import org.bukkit.entity.Player;

import java.util.Map;

public class CommandWithoutArgumentsExecutor {
    private final Map<String, Command> commandMap;

    public CommandWithoutArgumentsExecutor(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Handles commands that do not have any arguments.
     *
     * @param player The player who sent the command.
     * @return true, indicating the command was handled.
     */
    public boolean handleNoArgumentsCommand(Player player) {
        Command helpCommand = commandMap.get("help");
        if (helpCommand != null) {
            helpCommand.execute(player, new String[0]);
        } else {
            player.sendMessage("Help command not found.");
            // Or any other fallback mechanism
        }
        return true;
    }

}

