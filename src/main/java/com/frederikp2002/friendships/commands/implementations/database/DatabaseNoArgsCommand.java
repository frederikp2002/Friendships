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
        if (!configHandler.getBool("command.database.noargs.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.database.noargs.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.database.noargs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }
}
