package com.frederikp2002.friendships;

import com.frederikp2002.friendships.handlers.ICommandHandler;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import com.frederikp2002.friendships.handlers.implementations.CommandHandler;
import com.frederikp2002.friendships.handlers.implementations.ConfigHandler;
import com.frederikp2002.friendships.handlers.implementations.MessageHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private IMessageHandler messageHandler;
    private ICommandHandler commandHandler;
    private IConfigHandler configHandler;

    @Override
    public void onEnable() {
        this.messageHandler = new MessageHandler(this);
        this.configHandler = new ConfigHandler(this, messageHandler); // Initialize configHandler before commandHandler
        this.commandHandler = new CommandHandler(this, messageHandler, configHandler);
        Objects.requireNonNull(this.getCommand("friends")).setExecutor((CommandExecutor) this.commandHandler);
        getLogger().info(messageHandler.getMessage("plugin.enabled").content());
    }

    @Override
    public void onDisable() {
        getLogger().info(messageHandler.getMessage("plugin.disabled").content());
    }
}
