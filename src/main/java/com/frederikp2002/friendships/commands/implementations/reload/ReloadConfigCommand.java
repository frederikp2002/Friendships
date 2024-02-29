package com.frederikp2002.friendships.commands.implementations.reload;

import com.frederikp2002.friendships.commands.ICommand;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements ICommand {
    private final IMessageHandler messageHandler;
    private final IConfigHandler configHandler;

    public ReloadConfigCommand(IMessageHandler messageHandler, IConfigHandler configHandler) {
        this.messageHandler = messageHandler;
        this.configHandler = configHandler;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (!configHandler.getBool("command.reload.config.enabled")) {
            player.sendMessage(messageHandler.getMessage("command.reload.config.disabled"));
            return;
        }

        configHandler.reloadConfig();
        player.sendMessage(messageHandler.getMessage("command.reload.config.success"));

    }

    @Override
    public String[] getAliases() {
        return new String[]{"config", "conf"};
    }

    @Override
    public String[] getTabCompleteOptions(Player player, String[] args) {
        return new String[0];
    }

}