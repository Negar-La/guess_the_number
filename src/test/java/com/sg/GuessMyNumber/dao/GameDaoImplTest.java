package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.GuessMyNumberApplication;
import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import com.sg.GuessMyNumber.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GuessMyNumberApplication.class)
class GameDaoImplTest {
    private JdbcTemplate jdbcTemplate;
    private GameDao gameDao;
    private RoundDao roundDao;
    @Autowired
    public void GameDaoImplTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        gameDao = new GameDaoImpl(jdbcTemplate);
    }
    public GameDaoImplTest() {
    }

    @BeforeEach
    void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
        
    }

    @Test
    void addAndGetAllGames () {
        GameService gameService = new GameService(gameDao, roundDao);
        Game game = gameService.newGame();
        Game game2 = gameService.newGame();
        gameDao.addGame(game);
        gameDao.addGame(game2);
        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    void getGameById () {
        GameService service = new GameService(gameDao, roundDao);
        Game game = service.newGame();
        Game game2 = service.newGame();

        gameDao.addGame(game);
        gameDao.addGame(game2);

        gameDao.deleteGameById(game.getGameId());
        List<Game> games = gameDao.getAllGames();
        assertEquals(1, games.size());
    }


    @Test
    void updateGame () {
        GameService gameService = new GameService(gameDao, roundDao);
        Game game = gameService.newGame();
        gameDao.addGame(game);
        game.setFinished(true);
        gameDao.updateGame(game);
        Game updated = gameDao.getGameById(game.getGameId());
        assertTrue(updated.isFinished());
    }

    @Test
    void deleteGameById () {
        GameService gameService = new GameService(gameDao, roundDao);
        Game game = gameService.newGame();
        Game game2 = gameService.newGame();

        gameDao.addGame(game);
        gameDao.addGame(game2);

        gameDao.deleteGameById(game.getGameId());
        List<Game> games = gameDao.getAllGames();
        assertEquals(1,games.size());
    }
}