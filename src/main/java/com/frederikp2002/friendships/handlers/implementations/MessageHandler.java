package com.frederikp2002.friendships.handlers.implementations;

import com.frederikp2002.friendships.handlers.IMessageHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageHandler implements IMessageHandler {

    private final JavaPlugin plugin;
    private YamlConfiguration messagesConfig;

    public MessageHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    /**
     * Loads message configurations from a YAML file.
     * If the messages.yml file does not exist in the plugin's data folder,
     * it is created and loaded.
     */
    private void loadMessages() {
        // Define the path to the messages.yml file within the plugin's data folder.
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");

        // Check if the messages.yml file exists.
        if (!messagesFile.exists()) {
            // If not, create the file from the plugin's resources.
            plugin.saveResource("messages.yml", false);
            // Log an informational message indicating the creation of the file.
            plugin.getLogger().info("messages.yml was not found, so it has been created!");
        }

        // Load the configuration from the messages.yml file.
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    /**
     * Retrieves a formatted message from the messages' configuration.
     *
     * @param key  The key for the desired message.
     * @param args Arguments for formatting the message (if necessary).
     * @return A TextComponent representing the formatted message. If the key is not found,
     *         or if messagesConfig is null, an appropriate error message is returned.
     */
    public TextComponent getMessage(String key, Object... args) {
        // Check if the messages configuration is loaded.
        if (messagesConfig == null) { return Component.text("Messages not found :("); }

        // Retrieve the message string using the provided key.
        String message = messagesConfig.getString(key);

        // Check if the message exists and format it.
        if (message != null) {
            message = String.format(message, args);
            // Deserialize the message to create a TextComponent.
            return (TextComponent) MiniMessage.miniMessage().deserialize(message);
        } else {
            // Return an error message if the key is not found.
            return Component.text("Message not found: " + key);
        }
    }

    /**
     * Retrieves a list of formatted messages from the messages configuration.
     *
     * @param key The key for the desired message list.
     * @return A List of Components representing the formatted messages. If the key is not found,
     *         or if messagesConfig is null, a list containing an appropriate error message is returned.
     */
    public List<Component> getMessageListFormatted(String key) {
        // Check if the messages configuration is loaded.
        if (messagesConfig == null) { return Collections.singletonList(MiniMessage.miniMessage().deserialize("Messages not found!! :(")); }

        // Retrieve the list of messages using the provided key.
        List<String> messageList = messagesConfig.getStringList(key);

        // Check if the list is not empty.
        if (!messageList.isEmpty()) {
            List<Component> formattedMessages = new ArrayList<>();

            // Deserialize each message in the list to create Components.
            for (String message : messageList) {
                formattedMessages.add(MiniMessage.miniMessage().deserialize(message));
            }

            return formattedMessages;
        } else {
            // Return an error message if the key is not found.
            return Collections.singletonList(MiniMessage.miniMessage().deserialize("Messages not found: " + key));
        }
    }

}