package com.travaux.liarsdicebackend;

import com.travaux.liarsdicebackend.models.GameSettings;
import com.travaux.liarsdicebackend.models.Player;
import com.travaux.liarsdicebackend.services.GameService;
import com.travaux.liarsdicebackend.services.UtilService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.logging.Logger;

@SpringBootTest
class LiarsDiceBackendApplicationTests {
	Logger logger = Logger.getLogger(LiarsDiceBackendApplicationTests.class.getName());

	@MockBean
	UtilService utilService;

	@MockBean
	GameService gameService;

	@BeforeEach
	public void setUp(){
		this.utilService = new UtilService();
		this.gameService = new GameService(utilService);
	}

	public GameSettings newGameSettings() {
		Player p = new Player("Test Player");

		// Create a new game settings object
		return GameSettings.builder()
				.owner(p)
				.roomName("Test Room")
				.maxPlayers(4)
				.dicePerPlayer(5)
				.showTotalDice(true)
				.build();
	}

	public Player newPlayer() {
		return new Player(new StringBuilder()
				.append("Player ")
				.append((char) (Math.random() * 26 + 'a'))
				.append((char) (Math.random() * 26 + 'a'))
				.append((char) (Math.random() * 26 + 'a'))
				.append((char) (Math.random() * 26 + 'a'))
				.toString());
	}
}
