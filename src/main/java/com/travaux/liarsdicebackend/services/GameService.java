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

    public GameService(UtilService utilService) {
        this.games = new ArrayList<>();
        this.utilService = utilService;
    }

    public void addGame(Game game) {
        games.add(game);
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

    public String generateGameId() {
        String id = utilService.generateGameId();

        // Make sure the id is unique within the list of current games
        while (getGame(id) != null) {
            id = utilService.generateGameId();
        }

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
