package com.frederikp2002.friendships.handlers.implementations.commands.executors;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;
import java.util.Map;

public class CommandWithArgumentsExecutor {
    private final IMessageHandler messageHandler;
    private final Map<String, ICommand> commandMap;

    public CommandWithArgumentsExecutor(Map<String, ICommand> commandMap, IMessageHandler messageHandler) {
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

        ICommand command = commandMap.get(firstArg);
        if (command != null) {
            command.execute(player, args);
            return true;
        } else {
            player.sendMessage(messageHandler.getMessage("command.invalidArgument"));
            return false;
        }
    }

}
