package com.frederikp2002.friendships.handlers;

import java.util.List;

public interface IConfigHandler {
    String getString(String key);
    int getInt(String key);
    boolean getBool(String key);
    List<String> getStringList(String key);
    void reloadConfig();
}
