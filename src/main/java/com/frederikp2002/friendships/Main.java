package com.frederikp2002.friendships;

import com.frederikp2002.friendships.handlers.ICommandHandler;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import com.frederikp2002.friendships.handlers.implementations.commands.handlers.CommandHandler;
import com.frederikp2002.friendships.handlers.implementations.ConfigHandler;
import com.frederikp2002.friendships.handlers.implementations.DatabaseHandler;
import com.frederikp2002.friendships.handlers.implementations.MessageHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private IMessageHandler messageHandler;
    private ICommandHandler commandHandler;
    private IConfigHandler configHandler;
    private IDatabaseHandler databaseHandler;

    @Override
    public void onEnable() {
        this.messageHandler = new MessageHandler(this);
        this.configHandler = new ConfigHandler(this, messageHandler);
        this.databaseHandler = new DatabaseHandler(this, configHandler, messageHandler);
        this.commandHandler = new CommandHandler(this, messageHandler);
        Objects.requireNonNull(this.getCommand("friends")).setExecutor((CommandExecutor) this.commandHandler);
        database();
        getLogger().info(messageHandler.getMessage("plugin.enabled").content());
    }

    @Override
    public void onDisable() {
        getLogger().info(messageHandler.getMessage("plugin.disabled").content());
        databaseHandler.disconnect();
    }

    private void database() {
        try {
            databaseHandler.connect();
        } catch (Exception e) {
            getLogger().severe(messageHandler.getMessage("database.connecting.error").content());
            getLogger().severe(e.getMessage());
        }
    }

    public IMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public ICommandHandler getCommandHandler() {
        return commandHandler;
    }

    public IConfigHandler getConfigHandler() {
        return configHandler;
    }

    public IDatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

}
