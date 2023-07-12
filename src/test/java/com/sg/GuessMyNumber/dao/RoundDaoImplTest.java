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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GuessMyNumberApplication.class)
class RoundDaoImplTest {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private GameDao gameDao;
    @Autowired
    public void GameDaoImplTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        roundDao = new RoundDaoImpl(jdbcTemplate);
    }
    public RoundDaoImplTest() {
    }
    @BeforeEach
    void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }
    @Test
    void addTestAndGetAllRounds() {
        GameService gameService = new GameService(gameDao, roundDao);
        Game game = gameService.newGame();
        gameDao.addGame(game);
        Game game2 = gameService.newGame();
        gameDao.addGame(game2);
        Round round = new Round();
        round.setGuess("2035");
        round.setGameId(game.getGameId());
        round.setResult("e:2:p:2");
        LocalDate currentDate = LocalDate.now();
        Timestamp timestamp = Timestamp.valueOf(currentDate.atStartOfDay());
        round.setTimestamp(timestamp);
        Round round2 = new Round();
        round2.setGuess("1234");
        round2.setGameId(game2.getGameId());
        round2.setResult("e:1:p:0");
        round2.setTimestamp(timestamp);
        roundDao.addRound(round);
        roundDao.addRound(round2);
        List<Round> rounds = roundDao.getAllRounds();
        System.out.println(rounds);
        assertEquals(2, rounds.size());
    }
    @Test
    void getAllOfGame() {
    }
    @Test
    void getRoundById() {
    }
    @Test
    void updateRound() {
    }
    @Test
    void deleteRoundById() {
    }
}