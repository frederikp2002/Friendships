package com.frederikp2002.friendships.handlers.implementations.commands.handlers;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoCompleteHandler implements TabCompleter {
    Map<String, ICommand> commandMap;
    IMessageHandler messageHandler;

    public AutoCompleteHandler(Map<String, ICommand> commandMap, IMessageHandler messageHandler) {
        this.commandMap = commandMap;
        this.messageHandler = messageHandler;
    }

    /**
     * Handles the tab completion for commands.
     *
     * @param commandSender The sender of the command, which should be a player.
     * @param command The command that is being completed.
     * @param s The alias of the command which was used.
     * @param strings The arguments passed to the command.
     * @return A list of possible completions for the command, or null if the command sender is not a player.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            return null;
        }

        ICommand command1 = commandMap.get(strings[0]);
        if (command1 != null) {
            return List.of(command1.getTabCompleteOptions((Player) commandSender, strings));
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
