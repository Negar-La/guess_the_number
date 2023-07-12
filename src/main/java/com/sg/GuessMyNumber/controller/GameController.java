package com.sg.GuessMyNumber.controller;

import com.sg.GuessMyNumber.dao.GameDao;
import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import com.sg.GuessMyNumber.service.GameDataValidationException;
import com.sg.GuessMyNumber.service.GameService;
import com.sg.GuessMyNumber.service.RoundDataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Round guessNumber(@RequestBody Round body) throws GameDataValidationException, RoundDataValidationException {
        //implemented
        Game game = service.getGameById(body.getGameId());
        Round round = service.guessNumber(game, body.getGuess());
        return service.addRound(round);
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
    public Game getGameById(@PathVariable int id) {
       Game game = service.getGameById(id);
        return service.getGames(game);
    }

    @GetMapping("rounds/{gameId}")
    public List<Round> getGameRounds(@PathVariable int gameId) {
        return service.getAllOfGame(gameId);
    }

}



