package com.frederikp2002.friendships.handlers.implementations;

import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class ConfigHandler implements IConfigHandler {

    private final JavaPlugin plugin;
    IMessageHandler messageHandler;
    private YamlConfiguration config;

    public ConfigHandler(JavaPlugin plugin, IMessageHandler messageHandler) {
        this.plugin = plugin;
        this.messageHandler = messageHandler;
        loadConfig();
    }

    /**
     * Loads the configuration from a YAML file.
     * If the config.yml file does not exist in the plugin's data folder,
     * it is created and loaded.
     */
    private void loadConfig() {
        try {
            // Define the path to the config.yml file within the plugin's data folder.
            File configFile = new File(plugin.getDataFolder(), "config.yml");

            // Check if the config.yml file exists.
            if (!configFile.exists()) {
                // If not, create the file from the plugin's resources.
                plugin.saveResource("config.yml", false);
                // Log an informational message indicating the creation of the file.
                plugin.getLogger().info(messageHandler.getMessage("config.created").content());
            }

            // Load the configuration from the config.yml file.
            config = YamlConfiguration.loadConfiguration(configFile);

        } catch (Exception e) {
            // Log an error message if there's an issue loading the configuration.
            plugin.getLogger().severe(messageHandler.getMessage("config.error").content() + e.getMessage());
        }
    }

    /**
     * Retrieves a string value from the configuration.
     *
     * @param key The key for the desired string value.
     * @return The string value associated with the key, or null if not found.
     */
    public String getString(String key) {
        return config.getString(key);
    }

    /**
     * Retrieves an int value from the configuration.
     *
     * @param key The key for the desired int value.
     * @return The string value associated with the key, or null if not found.
     */
    public int getInt(String key) {
        return config.getInt(key);
    }

    /**
     * Retrieves a boolean value from the configuration.
     *
     * @param key The key for the desired boolean value.
     * @return The boolean value associated with the key, or false if not found.
     */
    public boolean getBool(String key) {
            return config.getBoolean(key);
    }

    /**
     * Retrieves a list of string values from the configuration.
     *
     * @param key The key for the desired list of string values.
     * @return The list of string values associated with the key, or an empty list if not found.
     */
    public List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    /**
     * Reloads the configuration from the file.
     */
    public void reloadConfig() {
        loadConfig();
    }

}
