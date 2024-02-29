package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;


public class DatabaseNoArgsCommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public DatabaseNoArgsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    public void DatabaseNoArgsFound(Player player) {
        if (!configHandler.getBool("command.database.noArgs.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.database.noArgs.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.database.noArgs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }
}
