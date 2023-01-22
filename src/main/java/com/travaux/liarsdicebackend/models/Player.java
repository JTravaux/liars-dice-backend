package com.travaux.liarsdicebackend.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Player {
    private String id;
    private String name;
    private boolean isReady;
    private List<Integer> dice;

    public Player(String name) {
        this.name = name;
        this.isReady = false;
        this.dice = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public void addDice(int dicePerPlayer) {
        for (int i = 0; i < dicePerPlayer; i++) {
            dice.add(1);
        }
    }

    public void rollDice() {
        for (int i = 0; i < dice.size(); i++) {
            dice.set(i, (int) (Math.random() * 6) + 1);
        }
    }
}
