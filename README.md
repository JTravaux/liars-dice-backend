### Liar's Dice Java Backend
This is the backend for the multiplayer [Liar's Dice](https://en.wikipedia.org/wiki/Liar%27s_dice) game. 
It is currently a work in progress that will be deployed to a server once complete. 
The backend is responsible for managing the game state, and communicating with the frontend via a REST api and websockets.

---

#### Running the server
To run the server, you must have Maven installed, and then run the following command in the root directory of the project:
```
mvn spring-boot:run
```

#### Running the tests
To run the tests, you must have Maven installed, and then run the following command in the root directory of the project:
```
mvn test
```