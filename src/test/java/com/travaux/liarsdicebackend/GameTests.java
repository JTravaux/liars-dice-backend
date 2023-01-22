package com.travaux.liarsdicebackend;

import com.travaux.liarsdicebackend.models.Game;
import com.travaux.liarsdicebackend.models.GameSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTests extends LiarsDiceBackendApplicationTests {

    @Test
    void createGame() {
        GameSettings settings = this.newGameSettings();

        // Create a new game
        String gameId = this.gameService.generateGameId();
        Game g = new Game(gameId, settings);

        logger.info("Created " + g.toString());

        // Make sure the game was created
        assert(g != null);

        // Make sure the game has the correct id
        assert(g.getId().equals(gameId));

        // Make sure the game has the correct settings
        assert(g.getSettings().equals(settings));

        // Make sure the game has one player
        assert(g.getPlayers().size() == 1);

        // Make sure the game has the correct owner
        assert(g.getPlayers().contains(settings.getOwner()));
    }

    @Test
    void maxPlayersInGame() {
        GameSettings settings = this.newGameSettings();

        // Create a new game
        String gameId = this.gameService.generateGameId();
        Game g = new Game(gameId, settings);

        // Add the max number of players to the game
        for (int i = 0; i < settings.getMaxPlayers() - 1; i++) {
            g.addPlayer(this.newPlayer());
        }

        // Make sure the game has the correct number of players
        assert(g.getPlayers().size() == settings.getMaxPlayers());

        Exception e = assertThrows(Exception.class, () -> {
            g.addPlayer(this.newPlayer());
        }, "Expected removeGame() to throw, but it didn't");

       assertTrue(e.getMessage().contains("Game is full"));
    }
}
