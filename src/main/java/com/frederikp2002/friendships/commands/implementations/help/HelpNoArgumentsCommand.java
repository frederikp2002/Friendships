package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpNoArgumentsCommand  {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public HelpNoArgumentsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    public void execute(Player player) {
        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help.noArgs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }

}
