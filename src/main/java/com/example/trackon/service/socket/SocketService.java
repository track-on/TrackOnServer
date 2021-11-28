package com.example.trackon.service.socket;

import com.corundumstudio.socketio.SocketIOClient;

public interface SocketService {
    void connect(SocketIOClient client);
    void disconnect(SocketIOClient client);
}
