package com.frederikp2002.friendships.handlers.implementations.commands.executors;

import com.frederikp2002.friendships.commands.ICommand;
import org.bukkit.entity.Player;

import java.util.Map;

public class CommandWithoutArgumentsExecutor {
    private final Map<String, ICommand> commandMap;

    public CommandWithoutArgumentsExecutor(Map<String, ICommand> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Handles commands that do not have any arguments.
     *
     * @param player The player who sent the command.
     * @return true, indicating the command was handled.
     */
    public boolean handleNoArgumentsCommand(Player player) {
        commandMap.get("help").execute(player, new String[0]);
        return true;
    }

}
