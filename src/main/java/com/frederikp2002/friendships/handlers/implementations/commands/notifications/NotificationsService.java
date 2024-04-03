package com.frederikp2002.friendships.handlers.implementations.commands.notifications;

import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class NotificationsService {
    private final JavaPlugin plugin;
    private final IMessageHandler messageHandler;

    public NotificationsService(JavaPlugin plugin, IMessageHandler messageHandler) {
        this.plugin = plugin;
        this.messageHandler = messageHandler;
    }

    /**
     * Notify the console that the command is player-only, if the console tries to execute it.
     */
    public void playersOnlyNotification() {
        // Log a warning message indicating that the command is player-only.
        plugin.getLogger().warning(messageHandler.getMessage("command.playersOnly").content());
    }

}
