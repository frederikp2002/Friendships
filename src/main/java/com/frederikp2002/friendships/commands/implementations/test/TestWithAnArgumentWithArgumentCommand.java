package com.frederikp2002.friendships.commands.implementations.test;

import com.frederikp2002.friendships.commands.Command;
import org.bukkit.entity.Player;

public class TestWithAnArgumentWithArgumentCommand extends Command {

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("Test command with argument THREE executed!");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"testarg3", "testargument3"};
    }

}
