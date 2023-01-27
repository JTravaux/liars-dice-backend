package com.travaux.liarsdicebackend.http;

import com.travaux.liarsdicebackend.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/gameplay")
public class GameplayController {
    private final GameService gameSetupService;

    public GameplayController(GameService gameSetupService) {
        this.gameSetupService = gameSetupService;
    }

    @GetMapping("/test")
    public List<String> getIds() {
        return gameSetupService.getGeneratedIds();
    }
}
