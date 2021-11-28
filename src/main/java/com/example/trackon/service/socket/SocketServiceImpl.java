package com.example.trackon.service.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.trackon.entity.user.User;
import com.example.trackon.entity.user.UserRepository;
import com.example.trackon.jwt.JwtProvider;
import com.example.trackon.payload.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

    private final UserRepository userRepository;

    private final SocketIOServer server;
    private final JwtProvider jwtProvider;

    @Override
    public void connect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("token");
        if(jwtProvider.validateToken(token)) {
            errorAndDisconnect(client, 403, "Invalid Token");
            return;
        }

        User user;
        try {
            user = userRepository.findByUserId(jwtProvider.getUserId(token))
                    .orElseThrow(RuntimeException::new);

            client.joinRoom("marker");

            client.set("user", user);
            printLog(client, "connected");
        }catch (Exception e) {
            errorAndDisconnect(client, 404, "User Not Found");
        }
    }

    @Override
    public void disconnect(SocketIOClient client) {
        printLog(client, "Disconnecting SessionId : " + client.getSessionId());
    }

    private void printLog(SocketIOClient client, String content) {
        log.info("SOCKET : " + client.getRemoteAddress().toString().substring(1) + " " + content);
    }

    private void errorAndDisconnect(SocketIOClient client, Integer status, String message) {
        client.sendEvent("error",
                ErrorResponse.builder()
                        .status(status)
                        .message(message)
                        .build()
        );

        log.error("STATE : " + status + " MESSAGE : " + message + " DISCONNECT SESSION_ID : " + client.getSessionId());
        client.disconnect();
    }
}
