package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpDatabaseCommand extends Command {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public HelpDatabaseCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.help.database.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.help.database.disabled"));
            return;
        }

        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help.database.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }

    }

    @Override
    public String[] getAliases() {
        return new String[]{"database", "db"};
    }

}