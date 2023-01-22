package com.travaux.liarsdicebackend.http;

import com.travaux.liarsdicebackend.models.Game;
import com.travaux.liarsdicebackend.models.GameSettings;
import com.travaux.liarsdicebackend.models.Player;
import com.travaux.liarsdicebackend.services.GameService;
import com.travaux.liarsdicebackend.services.UtilService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GamesController {

    private final GameService gameSetupService;

    public GamesController(GameService gameSetupService, UtilService utilService) {
        this.gameSetupService = gameSetupService;
    }

    @PostMapping("/create")
    public String createGame(@RequestBody CreateGameRecord record) {

        // Build the player
        Player p = new Player(record.ownerName());

        // Build the game settings
        GameSettings settings = GameSettings.builder()
                .owner(p)
                .roomName(record.roomName())
                .maxPlayers(record.maxPlayers())
                .dicePerPlayer(record.dicePerPlayer())
                .showTotalDice(record.showTotalDice())
                .build();

        // Create a new game with the settings and the owner
        Game g = new Game(gameSetupService.generateGameId(), settings);
        gameSetupService.addGame(g);

        // Return the game id
        return g.getId();
    }

    @PostMapping("/join/{id}")
    public boolean joinGame(@PathVariable String gameId, @RequestBody String playerName) {
        Game g = gameSetupService.getGame(gameId);
        if (g == null) throw new GameNotFoundException();

        Player p = new Player(playerName);
        g.addPlayer(p);

        return true;
    }
}
