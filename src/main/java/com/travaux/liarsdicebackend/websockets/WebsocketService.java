package com.travaux.liarsdicebackend.websockets;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WebsocketService {
    private final SimpMessagingTemplate simpleMessagingTemplate;

    @Autowired
    public WebsocketService(SimpMessagingTemplate simpleMessagingTemplate) {
        this.simpleMessagingTemplate = simpleMessagingTemplate;
    }

    public void notifyGame(final String gameId, final String message) {
        simpleMessagingTemplate.convertAndSend("/game/" + gameId, message);
    }

    public void notifyUser(final String id, final String message) {
        simpleMessagingTemplate.convertAndSendToUser(id, "/game", message);
    }
}
