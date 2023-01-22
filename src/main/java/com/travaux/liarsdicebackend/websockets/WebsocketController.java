package com.travaux.liarsdicebackend.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/game/{gameId}") // Inbound
    @SendTo("/game/{gameId}") // Outbound
    public String game(final String message) {
        return message;
    }
}
