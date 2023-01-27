package com.travaux.liarsdicebackend.websockets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebsocketMessage {
    private String gameId;
    private String playerId;
    private WebsocketMessageType type;

    // Some data
    private String data;
}
