package com.travaux.liarsdicebackend.websockets;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/game/{gameId}") // Inbound
    @SendTo("/game/{gameId}") // Outbound
    public String game(@DestinationVariable final String gameId, /*final WebsocketMessage message*/ final String message) {
        System.out.println("Received message for game " + gameId + ": " + message);
        return message;
    }

    @MessageMapping("/private-message") // Inbound
    @SendTo("/game/private-message") // Outbound
    public String player(@DestinationVariable final String gameId, @DestinationVariable final String playerId, final String message) {
        System.out.println("Received message for game " + gameId + " and player " + playerId + ": " + message);
        return message;
    }
}
