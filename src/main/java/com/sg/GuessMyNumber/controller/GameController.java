package com.sg.GuessMyNumber.controller;

import com.sg.GuessMyNumber.dao.GameDao;
import com.sg.GuessMyNumber.dao.RoundDao;
import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameDao gameDao;
    private final RoundDao roundDao;


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

    public void getAllGames(List<Game> games) {
        for (Game game : games) {
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }
    }

    public Round guessNumber(Game game, String guess, GameDao gameDao) {
        Round round = checkGuess(game, guess);
        if (game.isFinished()) {
            gameDao.updateGame(game);
        }
        setTimeStamp(round);
        round.setGameId(game.getGameId());

        return round;
    }

    public Round checkGuess(Game game, String guess) {
        return null;

    }
}
