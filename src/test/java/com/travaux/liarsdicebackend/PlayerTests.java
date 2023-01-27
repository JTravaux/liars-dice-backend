package com.travaux.liarsdicebackend;

import com.travaux.liarsdicebackend.models.Player;
import org.junit.jupiter.api.Test;

public class PlayerTests extends LiarsDiceBackendApplicationTests {

    @Test
    void createPlayer() {
        // Create a new player
        Player p = new Player("Test Player", "1234");

        logger.info("Created " + p.toString());

        // Make sure the player was created
        assert(p != null);

        // Make sure the player has the correct name
        assert(p.getName().equals("Test Player"));

        // Make sure the player has no dice
        assert(p.getDice().size() == 0);

        // Make sure the player has an id
        assert(p.getId() != null);
    }

    @Test
    void addDice() {
        // Create a new player
        Player p = new Player("Test Player", "1234");

        // Add 5 dice to the player
        p.addDice(5);

        // Make sure the player has 5 dice, each with a value of 1
        assert(p.getDice().size() == 5);
        assert (p.getDice().get(0) == 1);
    }

    @Test
    void rollDice() {
        // Create a new player
        Player p = new Player("Test Player", "1234");

        // Add 5 dice to the player
        p.addDice(5);

        // Roll the dice
        p.rollDice();

        // Make sure the player has 5 dice, each with a value between 1 and 6
        assert(p.getDice().size() == 5);

        // Make sure the dice are between 1 and 6
        for (int i = 0; i < p.getDice().size(); i++) {
            assert (p.getDice().get(i) >= 1 && p.getDice().get(i) <= 6);
        }
    }
}
