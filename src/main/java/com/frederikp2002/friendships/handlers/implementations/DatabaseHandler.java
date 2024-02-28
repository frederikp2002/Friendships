package com.frederikp2002.friendships.handlers.implementations;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler implements IDatabaseHandler {

    private final JavaPlugin plugin;
    private final IConfigHandler configHandler;
    private final IMessageHandler messageHandler;
    private Connection connection;

    public DatabaseHandler(JavaPlugin plugin, IConfigHandler configHandler, IMessageHandler messageHandler) {
        this.plugin = plugin;
        this.configHandler = configHandler;
        this.messageHandler = messageHandler;
    }

    public void connect() throws SQLException {
        final String HOST = configHandler.getString("host");
        final int PORT = configHandler.getInt("port");
        final String DATABASE = configHandler.getString("database");
        final String USERNAME = configHandler.getString("username");
        final String PASSWORD = configHandler.getString("password");

        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false", USERNAME, PASSWORD);
        plugin.getLogger().info(messageHandler.getMessage("database.connected").content());
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(1);
        } catch (SQLException e) {
            // Log the exception
            return false;
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                plugin.getLogger().info(messageHandler.getMessage("database.disconnected").content());
            } catch (SQLException e) {
                plugin.getLogger().severe(messageHandler.getMessage("database.disconnecting.error").content());
                plugin.getLogger().severe(e.getMessage());
            }
        }
    }


}
