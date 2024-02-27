package com.frederikp2002.friendships;

import com.frederikp2002.friendships.handlers.IMessageHandler;
import com.frederikp2002.friendships.handlers.implementations.MessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private IMessageHandler messageHandler;

    @Override
    public void onEnable() {
        this.messageHandler = new MessageHandler(this);
        getLogger().info(messageHandler.getMessage("plugin.enabled").content());

    }

    @Override
    public void onDisable() {
        getLogger().info(messageHandler.getMessage("plugin.disabled").content());
    }
}
