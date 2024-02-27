package com.frederikp2002.friendships.handlers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.List;

public interface IMessageHandler {
    TextComponent getMessage(String key, Object... args);
    List<Component> getMessageListFormatted(String key);
}
