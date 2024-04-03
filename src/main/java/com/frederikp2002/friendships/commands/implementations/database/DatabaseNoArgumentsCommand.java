package com.frederikp2002.friendships.commands.implementations.database;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class DatabaseNoArgumentsCommand extends Command {

    IMessageHandler messageHandler;
    IConfigHandler configHandler;

    public DatabaseNoArgumentsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }


    public void execute(Player player, String[] args) {
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