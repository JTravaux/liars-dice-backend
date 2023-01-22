package com.travaux.liarsdicebackend.http;

public record CreateGameRecord(String ownerName, String roomName, int maxPlayers, int dicePerPlayer, boolean showTotalDice) {
}
