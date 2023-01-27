package com.travaux.liarsdicebackend.http;

import com.travaux.liarsdicebackend.http.models.CreateGameRequest;
import com.travaux.liarsdicebackend.http.models.JoinGameRecord;
import com.travaux.liarsdicebackend.http.exceptions.*;
import com.travaux.liarsdicebackend.models.Game;
import com.travaux.liarsdicebackend.models.GameSettings;
import com.travaux.liarsdicebackend.models.Player;
import com.travaux.liarsdicebackend.services.GameService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/games")
public class GamesController {
    private final GameService gameSetupService;

    public GamesController(GameService gameSetupService) {
        this.gameSetupService = gameSetupService;
    }

    @GetMapping("/generate/id")
    public String generateId() {
        return gameSetupService.generateGameId();
    }

    @GetMapping("/exists/{id}")
    public boolean gameExists(@PathVariable @NotNull final String id) {
        return gameSetupService.getGame(id) != null;
    }

    @PostMapping("/create")
    public void createGame(@RequestBody @NotNull CreateGameRequest record) {

        // Validate the request roomId, roomName, ownerId, ownerName are required
        // Throw an exception if any of these are missing, and tell the user which one(s) is missing
        if (record.getRoomId() == null || record.getRoomId().isEmpty()) throw new MissingRoomIdException();
        if (gameSetupService.getGame(record.getRoomId()) != null) throw new GameExistsException();

        if (record.getRoomName() == null || record.getRoomName().isEmpty()) throw new MissingRoomNameException();
        if (record.getOwnerId() == null || record.getOwnerId().isEmpty()) throw new MissingOwnerIdException();
        if (record.getOwnerName() == null || record.getOwnerName().isEmpty()) throw new MissingOwnerNameException();

        // Build the player
        Player p = new Player(record.getOwnerName(), record.getOwnerId());

        // Build the game settings
        GameSettings settings = GameSettings.builder()
                .owner(p)
                .roomName(record.getRoomName())
                .maxPlayers(record.getMaxPlayers())
                .dicePerPlayer(record.getDicePerPlayer())
                .isTotalDiceVisible(record.isTotalDiceVisible())
                .isDicePerPlayerVisible(record.isDicePerPlayerVisible())
                .build();

        // Create a new game with the settings
        Game g = new Game(record.getRoomId(), settings);
        gameSetupService.addGame(g);
    }

    @PostMapping("/join/{gameId}")
    public void joinGame(@PathVariable @NotNull String gameId, @RequestBody @NotNull JoinGameRecord record) {
        Game g = gameSetupService.getGame(gameId);
        if (g == null) throw new GameNotFoundException();

        Player p = new Player(record.playerName(), record.playerId());
        g.addPlayer(p);
    }
}
