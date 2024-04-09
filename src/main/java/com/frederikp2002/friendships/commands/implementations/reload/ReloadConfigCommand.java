package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class ReloadConfigCommand extends Command {

    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public ReloadConfigCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        super("reload.config");
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        configHandler.reloadConfig();
        player.sendMessage(messageHandler.getMessage("command.reload.config.success"));
    }

}
