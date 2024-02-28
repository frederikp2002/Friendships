package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadNoArgsCommand {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public ReloadNoArgsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    public void ReloadNoArgsFound(Player player) {
        if (!configHandler.getBool("command.reload.noargs.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.reload.noargs.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.reload.noargs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }
}
