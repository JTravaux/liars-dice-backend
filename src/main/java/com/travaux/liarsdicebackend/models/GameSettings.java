package com.travaux.liarsdicebackend.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSettings {
    private Player owner;
    private String roomName;
    private int maxPlayers;
    private int dicePerPlayer;
    private boolean showTotalDice;
}
