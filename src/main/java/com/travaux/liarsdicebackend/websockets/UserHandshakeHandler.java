package com.travaux.liarsdicebackend.websockets;

import com.sun.security.auth.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = Logger.getLogger(UserHandshakeHandler.class.getName());

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        System.out.println("determineUser request: " + request);
        System.out.println("determineUser wsHandler: " + wsHandler);
        System.out.println("determineUser attributes: " + attributes);

        // TODO: Use the request to determine the user ID which is in the Player object
        String randomID = UUID.randomUUID().toString();
        LOG.info("User ID: " + randomID);

        return new UserPrincipal(randomID);
    }
}
