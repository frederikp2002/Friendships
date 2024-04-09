package com.frederikp2002.friendships.handlers.implementations.commands.handlers;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.TabCompletable;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AutoCompleteHandler implements TabCompleter {
    Map<String, Command> commandMap;
    IMessageHandler messageHandler;

    public AutoCompleteHandler(Map<String, Command> commandMap, IMessageHandler messageHandler) {
        this.commandMap = commandMap;
        this.messageHandler = messageHandler;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            return null;
        }

        if (args.length == 0) {
            // Handle root command completion
            return new ArrayList<>(commandMap.keySet()); // or some default list
        }

        // Find the deepest command that matches the args provided
        Command currentCommand = null;
        int argsIndex = 0;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase();
            if (i == 0) { // Root command
                currentCommand = commandMap.get(arg);
                argsIndex = 1;
            } else if (currentCommand != null) { // Subcommands
                Command subCommand = currentCommand.getSubcommands().get(arg);
                if (subCommand == null) {
                    break; // No further subcommand matches
                }
                currentCommand = subCommand;
                argsIndex = i + 1;
            }
        }

        if (currentCommand instanceof TabCompletable) {
            // Delegate to the current command's tabComplete method with the remaining args
            String[] subArgs = args.length > argsIndex ? Arrays.copyOfRange(args, argsIndex, args.length) : new String[0];
            return ((TabCompletable) currentCommand).tabComplete(subArgs);
        }

        List<Component> componentList = messageHandler.getMessageListFormatted("command.tabCompletionCommands");
        List<String> commandAliases = new ArrayList<>();
        for(Component component : componentList) {
            String textContent = PlainTextComponentSerializer.plainText().serialize(component);
            commandAliases.add(textContent);
        }

        return commandAliases;

    }


}
