package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class DatabaseNoArgumentsCommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public DatabaseNoArgumentsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    public void execute(Player player) {
        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.database.noArgs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }

}
