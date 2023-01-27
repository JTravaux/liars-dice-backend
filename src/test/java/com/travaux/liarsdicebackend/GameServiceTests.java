package com.travaux.liarsdicebackend;

import com.travaux.liarsdicebackend.models.Game;
import com.travaux.liarsdicebackend.models.GameSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameServiceTests extends LiarsDiceBackendApplicationTests {

    @Test
    void addsGameToService() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);

        // Add the game to the service
        this.gameService.addGame(g);

        // Make sure the game was added
        assert(this.gameService.getGame(g.getId()) != null);

        // Make sure the owner was added
        assert(g.getPlayers().contains(settings.getOwner()));
    }

    @Test
    void removeGameThatDoesNotExist() {
        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.removeGame("123");
        }, "Expected removeGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game not found"));
    }

    @Test
    void removeGameInProgress() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);
        g.setStatus(Game.Status.IN_PROGRESS);

        // Add the game to the service
        this.gameService.addGame(g);

        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.removeGame(g.getId());
        }, "Expected removeGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game is in progress"));
    }

    @Test
    void getGameThatDoesNotExist() {
        Game g = this.gameService.getGame("123");

        // Make sure the game was not found
        assert(g == null);
    }

    @Test
    void getExistingGame() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);

        // Add the game to the service
        this.gameService.addGame(g);

        // Make sure the game was found
        assert(this.gameService.getGame(g.getId()) != null);
    }

    @Test
    void startGameThatDoesNotExist() {
        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.startGame("123");
        }, "Expected startGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game not found"));
    }

    @Test
    void StartGameInProgress() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);
        g.setStatus(Game.Status.IN_PROGRESS);

        // Add the game to the service
        this.gameService.addGame(g);

        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.startGame(g.getId());
        }, "Expected startGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game is already in progress"));
    }

    @Test
    void startGameWithOnePlayer() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);

        // Add the game to the service
        this.gameService.addGame(g);

        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.startGame(g.getId());
        }, "Expected startGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game must have at least 2 players"));
    }

    @Test
    void startGame() throws Exception {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);
        g.addPlayer(this.newPlayer());

        // Add the game to the service
        this.gameService.addGame(g);

        // Start the game
        this.gameService.startGame(g.getId());

        // Make sure the game is in progress
        assert(g.getStatus() == Game.Status.IN_PROGRESS);
    }

    @Test
    void finishGameThatDoesNotExist() {
        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.finishGame("123");
        }, "Expected finishGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game not found"));
    }

    @Test
    void finishGameNotInProgress() {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);

        // Add the game to the service
        this.gameService.addGame(g);

        Exception e = assertThrows(Exception.class, () -> {
            this.gameService.finishGame(g.getId());
        }, "Expected finishGame() to throw, but it didn't");

        // Make sure the exception was thrown
        assertTrue(e.getMessage().contains("Game is not in progress"));
    }

    @Test
    void finishGame() throws Exception {
        GameSettings settings = this.newGameSettings();
        Game g = new Game(this.gameService.generateGameId(), settings);
        g.addPlayer(this.newPlayer());
        g.setStatus(Game.Status.IN_PROGRESS);

        // Add the game to the service
        this.gameService.addGame(g);

        // Finish the game
        this.gameService.finishGame(g.getId());

        // Make sure the game is finished
        assert(g.getStatus() == Game.Status.FINISHED);
    }

    @Test
    void GameIdIsUnique() {
        GameSettings settings = this.newGameSettings();

        // Generate new games and add them to the service
        int numGames = 100;
        for (int i = 0; i < numGames; i++) {
            Game g = new Game(this.gameService.generateGameId(), settings);
            this.gameService.addGame(g);
        }

        // Make sure all the games have unique IDs
        assert (this.gameService.getGames().size() == numGames);
        assert (this.gameService.getGames().stream().map(Game::getId).distinct().count() == numGames);
    }
}
