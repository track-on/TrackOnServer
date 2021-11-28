package com.example.trackon.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class SocketConfig {

    private SocketIOServer server;

    @Value("${socket.port}")
    private int port;

    @Bean
    public SocketIOServer SocketIOserver() {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();

        configuration.setPort(port);
        configuration.setOrigin("*");

        server = new SocketIOServer(configuration);

        server.start();

        return server;
    }

    @PreDestroy
    public void stop() {
        server.stop();
    }
}
