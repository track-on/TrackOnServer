package com.example.trackon.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.trackon.service.socket.SocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class SocketController {

    private final SocketService service;
    private final SocketIOServer server;

    @PostConstruct
    public void socket() {
        server.addConnectListener(service::connect);
        server.addDisconnectListener(service::disconnect);
    }
}
