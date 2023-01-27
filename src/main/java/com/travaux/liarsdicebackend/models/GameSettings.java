package com.travaux.liarsdicebackend.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSettings {
    private String roomName;

    private Player owner;

    private int maxPlayers;
    private int dicePerPlayer;

    private boolean isTotalDiceVisible;
    private boolean isDicePerPlayerVisible;
}
