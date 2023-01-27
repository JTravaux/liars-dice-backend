package com.travaux.liarsdicebackend;

import com.travaux.liarsdicebackend.models.Game;
import org.junit.jupiter.api.Test;

public class GamesControllerTests extends LiarsDiceBackendApplicationTests {

    @Test
    void generateIdTest() {
        String id = this.gamesController.generateId();
        assert(id != null);
        assert(id.length() == 4);
    }

    @Test
    void checkIdTest() {
        String id = this.gamesController.generateId();
        boolean result = this.gamesController.gameExists(id);
        assert(result == false);

        Game g = new Game(id, this.newGameSettings());
        this.gameService.addGame(g);
        result = this.gamesController.gameExists(id);
        assert(result == true);
    }
}
