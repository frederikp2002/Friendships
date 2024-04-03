package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadNoArgumentsCommand extends Command {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public ReloadNoArgumentsCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.reload.noArgs.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.reload.noArgs.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.reload.noArgs.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }

}
