package com.travaux.liarsdicebackend.websockets;

import com.travaux.liarsdicebackend.models.Player;
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

    public void notifyUser(final Player playerToNotify, final String message) {
        simpleMessagingTemplate.convertAndSendToUser(playerToNotify.getId(), "/game/private-message", message);
    }
}
