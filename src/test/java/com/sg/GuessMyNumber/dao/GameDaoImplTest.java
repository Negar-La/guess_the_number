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

//        List<Round> rounds = roundDao.getAllRounds();
//        for (Round round : rounds) {
//            gameDao.deleteGameById(round.getRoundId());
//        }
    }

//    @Test
//    void addGame () {
//
//    }

    @Test
    void addAndGetAllGames () {
        //implemented
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
    }


    @Test
    void updateGame () {
    }

    @Test
    void deleteGameById () {
    }
}