package com.frederikp2002.friendships.handlers.implementations.commands.executors;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;

public class CommandWithArgumentsExecutor {
    private final IMessageHandler messageHandler;
    private final Map<String, Command> commandMap;

    public CommandWithArgumentsExecutor(Map<String, Command> commandMap, IMessageHandler messageHandler) {
        this.commandMap = commandMap;
        this.messageHandler = messageHandler;
    }

    /**
     * Handles commands that do have arguments.
     *
     * @param player The player who sent the command.
     * @return true, indicating the command was handled.
     */
    public boolean handleCommandWithArguments(Player player, String[] args) {
        String firstArg = args[0].toLowerCase();
        Command command = commandMap.get(firstArg);
        if (command != null) {
            // Remove the first element (the command itself) before passing the rest as arguments
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            command.processCommand(player, subArgs);
            return true;
        } else {
            player.sendMessage(messageHandler.getMessage("command.invalidArgument"));
            return false;
        }
    }
}

