package com.sg.GuessMyNumber.controller;

import com.sg.GuessMyNumber.dao.GameDao;
import com.sg.GuessMyNumber.dao.RoundDao;
import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import com.sg.GuessMyNumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameDao gameDao;
    private final RoundDao roundDao;

    @Autowired
    public GameController(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game create() {
        //implemented create gameService object and game object
        GameService gameService = new GameService ();
        Game game = gameService.newGame();
        //added to database
        gameDao.addGame(game);
        //getGame will hide answer before returning it to the user
        return gameService.getGames(game);
    }


    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guessNumber(@RequestBody Round body) {
        //implemented
        Game game = gameDao.getGameById(body.getGameId());
        GameService gameService =new GameService();
        Round round = gameService.guessNumber(game, body.getGuess(), gameDao);
        return roundDao.addRound(round);
    }

    @GetMapping("/game")
    public List<Game> all() {
        List<Game> games = gameDao.getAllGames();
        GameService service = new GameService();
        service.getAllGames(games);
        return games;
    }

    @GetMapping("game/{id}")
    public Game getGameById(@PathVariable int id) {
        Game game = gameDao.getGameById(id);
        GameService service = new GameService();
        return service.getGames(game);
    }

    @GetMapping("rounds/{gameId}")
    public List<Round> getGameRounds(@PathVariable int gameId) {
        return roundDao.getAllOfGame(gameId);
    }

}



