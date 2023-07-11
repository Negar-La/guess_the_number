package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbcTemplate.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameId = ?";
            return jdbcTemplate.queryForObject(SELECT_GAME_BY_ID, new Object[]{id}, new GameMapper());
        } catch(DataAccessException ex) {
            return null;
        }

    }

    @Override
    public Game addGame(Game game) {

        final String INSERT_GAME = "INSERT INTO game(answer, isFinished) VALUES(?,?)";
        jdbcTemplate.update(INSERT_GAME,
                game.getAnswer(),
                game.isFinished());

        // Retrieve the last inserted ID

        String selectLastIdQuery = "SELECT LAST_INSERT_ID()";
        int gameId = jdbcTemplate.queryForObject(selectLastIdQuery, Integer.class);

        // Set the game ID
        game.setGameId(gameId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET answer = ?, isFinished = ? WHERE gameId = ?";
        jdbcTemplate.update(UPDATE_GAME,
                game.getAnswer(),
                game.isFinished(),
                game.getGameId());
    }

    @Override
    public void deleteGameById(int id) {

        final String DELETE_GAME = "DELETE FROM game WHERE gameId=?";
        jdbcTemplate.update(DELETE_GAME, id);

    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("isFinished"));
            return game;
        }
    }
}
