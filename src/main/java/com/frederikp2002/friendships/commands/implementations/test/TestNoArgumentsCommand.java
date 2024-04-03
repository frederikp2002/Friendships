package com.frederikp2002.friendships.commands.implementations.test;

import com.frederikp2002.friendships.commands.Command;
import org.bukkit.entity.Player;

public class TestNoArgumentsCommand extends Command {

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("Test command without argument executed!");
    }

}
