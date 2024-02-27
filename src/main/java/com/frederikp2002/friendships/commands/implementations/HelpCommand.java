package com.frederikp2002.friendships.commands.implementations;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements ICommand {
    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public HelpCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.help.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.help.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }
}
