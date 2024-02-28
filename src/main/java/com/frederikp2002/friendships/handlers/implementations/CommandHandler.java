package com.frederikp2002.friendships.handlers.implementations;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.commands.implementations.HelpCommand;
import com.frederikp2002.friendships.commands.implementations.database.DatabaseCommand;
import com.frederikp2002.friendships.commands.implementations.reload.ReloadConfigCommand;
import com.frederikp2002.friendships.handlers.ICommandHandler;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements CommandExecutor, ICommandHandler {

    private final JavaPlugin plugin;
    private final IMessageHandler messageHandler;
    private final Map<String, ICommand> commandMap = new HashMap<>();

    public CommandHandler(JavaPlugin plugin, IMessageHandler messageHandler, IConfigHandler configHandler, IDatabaseHandler databaseHandler) {
        this.plugin = plugin;
        this.messageHandler = messageHandler;
        commandMap.put("help", new HelpCommand(messageHandler, configHandler));
        commandMap.put("reloadconfig", new ReloadConfigCommand(messageHandler, configHandler));
        commandMap.put("database", new DatabaseCommand(messageHandler, configHandler, databaseHandler));
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            playersOnlyNotification();
            return false;
        }

        // Check if the command is the expected "friends" command.
        if (!(command.getName().equalsIgnoreCase("friends"))) return false;

        Player player = (Player) commandSender;

        // Handle commands based on whether they have arguments.
        if (args.length == 0) {
            return handleNoArgumentsCommand(player);
        } else {
            return handleCommandWithArguments(player, args);
        }
    }

    /**
     * Handles commands that do not have any arguments.
     *
     * @param player The player who sent the command.
     * @return true, indicating the command was handled.
     */
    private boolean handleCommandWithArguments(Player player, String[] args) {
        String firstArg = args[0].toLowerCase(); // Assuming case-insensitive commands

        ICommand command = commandMap.get(firstArg);
        if (command != null) {
            command.execute(player, args);
            return true;
        } else {
            player.sendMessage(messageHandler.getMessage("commandHandler.invalidArgument"));
            return false;
        }
    }

    /**
     * Handles commands that do not have any arguments.
     *
     * @param player The player who sent the command.
     * @return true, indicating the command was handled.
     */
    private boolean handleNoArgumentsCommand(Player player) {
        commandMap.get("help").execute(player, new String[0]);
        return true;
    }

    /**
     * Sends a notification to the sender that the command is only available to players.
     */
    private void playersOnlyNotification() {
        // Log a warning message indicating that the command is player-only.
        plugin.getLogger().warning(messageHandler.getMessage("commandHandler.playersOnly").content());
    }

}

