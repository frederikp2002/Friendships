package com.frederikp2002.friendships.commands;

import java.util.List;

public interface TabCompletable {
    List<String> tabComplete(String[] args);
}