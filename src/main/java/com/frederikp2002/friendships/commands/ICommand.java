package com.frederikp2002.friendships.commands;

import org.bukkit.entity.Player;

public interface ICommand {
    void execute(Player player, String[] args);
    String[] getAliases();
}
