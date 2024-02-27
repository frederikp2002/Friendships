package com.frederikp2002.friendships.commands.implementations;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements ICommand {
    private final IMessageHandler messageHandler;

    public HelpCommand(IMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }
}
