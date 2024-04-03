package com.frederikp2002.friendships.handlers.implementations.commands.handlers;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.commands.implementations.help.HelpCommand;
import com.frederikp2002.friendships.commands.implementations.database.DatabaseCommand;
import com.frederikp2002.friendships.commands.implementations.reload.ReloadCommand;
import com.frederikp2002.friendships.commands.implementations.test.TestCommand;
import com.frederikp2002.friendships.handlers.ICommandHandler;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import com.frederikp2002.friendships.handlers.implementations.commands.executors.CommandWithArgumentsExecutor;
import com.frederikp2002.friendships.handlers.implementations.commands.executors.CommandWithoutArgumentsExecutor;
import com.frederikp2002.friendships.handlers.implementations.commands.notifications.NotificationsService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements TabExecutor, ICommandHandler {
    private final CommandWithArgumentsExecutor commandWithArgumentsExecutor;
    private final CommandWithoutArgumentsExecutor commandWithoutArgumentsExecutor;
    private final AutoCompleteHandler autoCompleteHandler;
    private final NotificationsService notificationsService;
    private final Map<String, ICommand> commandMap = new HashMap<>();

    public CommandHandler(JavaPlugin plugin, IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.commandWithArgumentsExecutor = new CommandWithArgumentsExecutor(commandMap, messageHandler);
        this.commandWithoutArgumentsExecutor = new CommandWithoutArgumentsExecutor(commandMap);
        this.autoCompleteHandler = new AutoCompleteHandler(commandMap, messageHandler);
        this.notificationsService = new NotificationsService(plugin, messageHandler);
        registerCommand(new DatabaseCommand(messageHandler, configHandler, databaseHandler));
        registerCommand(new ReloadCommand(messageHandler, configHandler));
        registerCommand(new HelpCommand(messageHandler, configHandler));
        registerCommand(new TestCommand());
    }

    private void registerCommand(ICommand command) {
        for (String alias : command.getAliases()) {
            commandMap.put(alias, command);
        }
    }

    /**
     * Handles incoming commands.
     *
     * @param commandSender The sender of the command.
     * @param command The command that was executed.
     * @param label The alias of the command which was used.
     * @param args The arguments passed to the command.
     * @return true if the command was processed successfully, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,
                             @NotNull Command command,
                             @NotNull String label,
                             String[] args) {
        if (!(commandSender instanceof Player player)) {
            notificationsService.playersOnlyNotification();
            return false;
        }

        // Check if the command is the expected "friends" command.
        if (!(command.getName().equalsIgnoreCase("friends"))) return false;

        // Handle commands based on whether they have arguments.
        if (args.length == 0) {
            return commandWithoutArgumentsExecutor.handleNoArgumentsCommand(player);
        } else {
            return commandWithArgumentsExecutor.handleCommandWithArguments(player, args);
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender,
                                                @NotNull Command command,
                                                @NotNull String s,
                                                String[] strings) {
        return autoCompleteHandler.onTabComplete(commandSender, command, s, strings);
    }
}