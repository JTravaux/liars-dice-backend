package com.travaux.liarsdicebackend.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private String id;

    private List<Player> players;
    private Player currentPlayer;
    private GameSettings settings;

    private Status status;
    public enum Status { PRE_GAME, IN_PROGRESS, FINISHED }

    public Game(String id, GameSettings settings) {
        this.id = id;
        this.settings = settings;
        this.status = Status.PRE_GAME;

        // Initialize the players list with specified settings
        this.players = new ArrayList<>(settings.getMaxPlayers() - 1);
        this.players.add(settings.getOwner());
    }

    public void addPlayer(Player player) {
        if (players.size() == settings.getMaxPlayers())
            throw new RuntimeException("Game is full");

        players.add(player);
    }
}
