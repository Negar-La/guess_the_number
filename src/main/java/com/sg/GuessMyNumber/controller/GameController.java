package com.sg.GuessMyNumber.controller;

import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import com.sg.GuessMyNumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game create() {
        //implemented create gameService object and game object
        Game game = service.newGame();
        //added to database
        service.addGame(game);
        //getGame will hide answer before returning it to the user
        return service.getGames(game);
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> guessNumber(@RequestBody Round body) throws Exception {
        //implemented

        Game game = service.getGameById(body.getGameId());
        if(game == null){
            return new ResponseEntity("Game not found.", HttpStatus.NOT_FOUND);
        }
        Round round = service.guessNumber(game, body.getGuess());
        if (round.getGuess().length() !=4 ) {
            return new ResponseEntity("Guess object is not valid, it should be 4 digits", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (game.getAnswer().length() != 4){
            return new ResponseEntity("Game object is not valid, it should be 4 digits", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //the chars() method converts the string into an IntStream of characters.
        // The distinct() method ensures that only distinct characters are considered.
        // Finally, the count() method is used to count the number of distinct characters.
        if ((round.getGuess()).chars().distinct().count() != 4) {
            return new ResponseEntity("Guess should contain 4 different numbers.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (game.isFinished()){
            return new ResponseEntity("You cannot guess for a finished game.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
         service.addRound(round);
         return ResponseEntity.ok(round);
    }

    @GetMapping("/game")
    public List<Game> all() {
        List<Game> games = service.getAllGames();
        for (Game game : games) {
            // Do not show answer when game is in progress.
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }
        return games;
    }

    @GetMapping("game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
       Game game = service.getGameById(id);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        if (!game.isFinished()) {
            game.setAnswer("****");
        }

        return ResponseEntity.ok(game);
    }

    @GetMapping("rounds/{gameId}")
    public ResponseEntity  <List<Round>> getGameRounds(@PathVariable int gameId) {
        Game game = service.getGameById(gameId);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        List<Round> rounds = service.getAllOfGame(gameId);
        return ResponseEntity.ok(rounds);
    }
}



