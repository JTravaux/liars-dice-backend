package com.travaux.liarsdicebackend.http.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateGameRequest {
    private String roomId;
    private String roomName;

    private String ownerId;
    private String ownerName;

    private int maxPlayers;
    private int dicePerPlayer;

    private boolean isTotalDiceVisible;
    private boolean isDicePerPlayerVisible;
}
