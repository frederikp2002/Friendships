package com.frederikp2002.friendships.commands.implementations.help;

import com.frederikp2002.friendships.commands.Command;
import com.frederikp2002.friendships.commands.TabCompletable;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpReloadCommand extends Command implements TabCompletable {

    public HelpReloadCommand() {
        super("help.reload");
    }

    @Override
    public void execute(Player player, String[] args) {
        List<Component> helpMessages = messageHandler.getMessageListFormatted("command.help.reload.list");
        for (Component message : helpMessages) {
            player.sendMessage(message);
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return super.tabComplete(args);
    }


}
