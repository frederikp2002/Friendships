package com.frederikp2002.friendships.commands.implementations.test;

import com.frederikp2002.friendships.commands.Command;
import org.bukkit.entity.Player;

public class TestWithAnotherArgumentCommand extends Command {

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("Test command with argument2 executed!");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"testarg2", "testargument2"};
    }

}
