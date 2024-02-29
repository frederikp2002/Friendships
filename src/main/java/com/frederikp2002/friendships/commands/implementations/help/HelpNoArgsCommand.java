package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpNoArgsCommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public HelpNoArgsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    public void HelpNoArgsFound(Player player) {
        if (!configHandler.getBool("command.help.noArgs.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.help.noArgs.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help.noArgs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }

}
