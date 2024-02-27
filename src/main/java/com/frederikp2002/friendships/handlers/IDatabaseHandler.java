package com.frederikp2002.friendships.handlers;

public interface IDatabaseHandler {
    void connect() throws Exception;
    void disconnect();
    boolean isConnected();
}
