package com.frederikp2002.friendships.commands;

import com.frederikp2002.friendships.Main;
import com.frederikp2002.friendships.handlers.IConfigHandler;
import com.frederikp2002.friendships.handlers.IDatabaseHandler;
import com.frederikp2002.friendships.handlers.IMessageHandler;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Command {

    protected String commandName;
    protected List<String> aliases;
    protected String permission;
    protected String description;
    protected String syntax;
    protected final IMessageHandler messageHandler = Main.getPlugin(Main.class).getMessageHandler();
    protected final IConfigHandler configHandler = Main.getPlugin(Main.class).getConfigHandler();
    protected final IDatabaseHandler databaseHandler = Main.getPlugin(Main.class).getDatabaseHandler();
    private final Map<String, Command> subcommands = new HashMap<>();

    public Command(String commandName) {
        this.commandName = commandName;
        this.aliases = getAliases();
        this.permission = getPermission();
        this.description = getDescription();
        this.syntax = getSyntax();
    }

    public abstract void execute(Player player, String[] args);

    public void addSubCommand(Command subCommand) {
        for (String alias : subCommand.getAliases()) {
            subcommands.put(alias.toLowerCase(), subCommand);
        }
    }

    public void processCommand(Player player, String[] args) {
        if (args.length > 0) {
            String subCommandKey = args[0].toLowerCase();
            Command subCommand = subcommands.get(subCommandKey);
            if (subCommand != null) {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                subCommand.processCommand(player, subArgs);
            } else {
                this.execute(player, new String[]{});
            }
        } else {
            this.execute(player, args);
        }
    }

    protected void checkForSubcommands(Player player, String[] args) {
        if (args.length > 0) {
            String subCommandKey = args[0].toLowerCase();
            if (!this.getAliases().contains(subCommandKey)) {
                this.processCommand(player, args);
            }
        }
    }

    public List<String> tabComplete(String[] args) {
        if (args.length == 1) {
            // Provide direct subcommand completions
            return getSubcommands().values().stream()
                    .flatMap(cmd -> cmd.getAliases().stream())
                    .collect(Collectors.toList());
        } else if (args.length > 1) {
            // Delegate to the subcommand for further completion
            String subCommandKey = args[0].toLowerCase();
            Command subCommand = getSubcommands().get(subCommandKey);
            if (subCommand == null) {
                // Try finding by alias if not found by direct name
                for (Command possibleSubCommand : getSubcommands().values()) {
                    if (possibleSubCommand.getAliases().contains(subCommandKey)) {
                        subCommand = possibleSubCommand;
                        break;
                    }
                }
            }
            if (subCommand instanceof TabCompletable) {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                return ((TabCompletable) subCommand).tabComplete(subArgs);
            }
        }
        return Collections.emptyList();
    }

    // Getter for subcommands
    public Map<String, Command> getSubcommands() {
        return subcommands;
    }

    public List<String> getAliases() {
        return configHandler.getStringList("command." + commandName + ".aliases");
    }

    public String getPermission() {
        return configHandler.getString("command." + commandName + ".permission");
    }

    public String getDescription() {
        return configHandler.getString("command." + commandName + ".description");
    }

    public String getSyntax() {
        return configHandler.getString("command." + commandName + ".syntax");
    }

}
