package com.travaux.liarsdicebackend.services;

import com.travaux.liarsdicebackend.models.Game;
import com.travaux.liarsdicebackend.models.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private List<Game> games;
    private final UtilService utilService;

    // Using a separate list because an ID may be generated but not used in a game yet
    // This is to prevent the same ID from being generated twice on the UI
    private List<String> generatedIds;

    public GameService(UtilService utilService) {
        this.games = new ArrayList<>();
        this.utilService = utilService;
        this.generatedIds = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
        generatedIds.remove(game.getId());
    }

    public void removeGame(String id) throws Exception {
        Game g = getGame(id);

        if (g == null) throw new Exception("Game not found");
        if (g.getStatus() == Game.Status.IN_PROGRESS) throw new Exception("Game is in progress");

        games.removeIf(game -> game.getId().equals(g.getId()));
    }

    public Game getGame(String id) {
        return games.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }
    public List<String> getGeneratedIds() {
        return generatedIds;
    }

    public List<Game> getGames() {
        return games;
    }

    public String generateGameId() {
        String id = utilService.generateGameId();

        // Make sure the id is unique within the list of current generated ids
        while (generatedIds.contains(id)) {
            id = utilService.generateGameId();
        }

        // Add the id to the list of generated ids
        generatedIds.add(id);

        return id;
    }

    public void startGame(String id) throws Exception {
        Game g = getGame(id);
        if (g == null) throw new Exception("Game not found");
        if (g.getStatus() != Game.Status.PRE_GAME) throw new Exception("Game is already in progress");
        if (g.getPlayers().size() < 2) throw new Exception("Game must have at least 2 players");

        for (Player p : g.getPlayers()) {
            p.addDice(g.getSettings().getDicePerPlayer());
        }

        g.setStatus(Game.Status.IN_PROGRESS);
    }

    public void finishGame(String id) throws Exception {
        Game g = getGame(id);
        if (g == null) throw new Exception("Game not found");
        if (g.getStatus() != Game.Status.IN_PROGRESS) throw new Exception("Game is not in progress");

        g.setStatus(Game.Status.FINISHED);
    }
}
