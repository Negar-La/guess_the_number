package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.dto.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GameDaoImpl implements GameDao{
    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Game getGameById(int id) {
        return null;
    }

    @Override
    public Game addGame(Game game) {
        return null;
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGameById(int id) {

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
