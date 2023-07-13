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
import java.time.LocalDateTime;
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
//        arrange
        GameService gameService = new GameService(gameDao, roundDao);

//        act (some arrange mixed in)
        Game game = gameService.newGame();
        gameDao.addGame(game);

        Game game2 = gameService.newGame();
        gameDao.addGame(game2);

        Round round = new Round();
        round.setGuess("2035");
        round.setGameId(game.getGameId());
        round.setResult("e:2:p:2");
        LocalDateTime currentDateTime = LocalDateTime.now();
        round.setTimestamp(Timestamp.valueOf(currentDateTime));

        Round round2 = new Round();
        round2.setGuess("1234");
        round2.setGameId(game2.getGameId());
        round2.setResult("e:1:p:0");
        round2.setTimestamp(Timestamp.valueOf(currentDateTime));

        roundDao.addRound(round);
        roundDao.addRound(round2);

//        assert
        List<Round> rounds = roundDao.getAllRounds();
        System.out.println(rounds);

        assertEquals(2, rounds.size());
    }

    @Test
    void getAllOfGame() {
//      ARRANGE:   add a game
        GameService gameService = new GameService(gameDao, roundDao);
//        using LocalDate Class
        LocalDateTime currentDateTime = LocalDateTime.now();

//        Game game = gameService.newGame();
//        gameDao.addGame(game);
//
        Game game2 = gameService.newGame();
        gameDao.addGame(game2);

//        creating rounds for the game
        Round round = new Round();
        round.setGameId(game2.getGameId());
        round.setTimestamp(Timestamp.valueOf(currentDateTime));
        round.setGuess("1234");
        round.setResult("e:0:p:0");

        Round round2 = new Round();
        round2.setGameId(game2.getGameId());
        round2.setTimestamp(Timestamp.valueOf(currentDateTime));
        round2.setGuess("2356");
        round2.setResult("e:0:p:2");


        Round round3 = new Round();
        round3.setGameId(game2.getGameId());
        round3.setTimestamp(Timestamp.valueOf(currentDateTime));
        round3.setGuess("5689");
        round3.setResult("e:2:p:1");

        Round round4 = new Round();
        round4.setGameId(game2.getGameId());
        round4.setTimestamp(Timestamp.valueOf(currentDateTime));
        round4.setGuess("5678");
        round4.setResult("e:4:p:0");

// adding the round to DAO
        roundDao.addRound(round);
        roundDao.addRound(round2);
        roundDao.addRound(round3);
        roundDao.addRound(round4);


//        getting all rounds by gameID
        List<Round> rounds = roundDao.getAllOfGame(round.getGameId());
        System.out.println(rounds);

        assertEquals(4, rounds.size());
    }

  /*  @Test
    void deleteRoundById() {
        GameService gameService = new GameService(gameDao, roundDao);
        Game game = gameService.newGame();
        gameDao.addGame(game);
        Round round = new Round();
        round.setGuess("2035");
        round.setGameId(game.getGameId());
        round.setResult("e:2:p:2");
        LocalDate currentDate = LocalDate.now();
        Timestamp timestamp = Timestamp.valueOf(currentDate.atStartOfDay());
        round.setTimestamp(timestamp);
        roundDao.addRound(round);

        roundDao.deleteRoundById(game.getGameId());
        List<Round> rounds = roundDao.getAllRounds();
        assertEquals(1, rounds.size());
    } */
}