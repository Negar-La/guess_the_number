package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.dto.Round;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoundDaoImpl implements RoundDao{

    private final JdbcTemplate jdbcTemplate;

    public RoundDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Round> getAllRounds() {

        final String SELECT_ALL_ROUNDS = "SELECT * FROM round";
        return jdbcTemplate.query(SELECT_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {

        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundId = ?";
            return jdbcTemplate.queryForObject(SELECT_ROUND_BY_ID, new Object[]{id}, new RoundMapper());
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round addRound(Round round) {

        final String INSERT_ROUND = "INSERT INTO round(gameId, guessTime, guess, result) VALUES(?,?,?,?)";
        jdbcTemplate.update(INSERT_ROUND,
                round.getGameId(),
                round.getTimestamp(),
                round.getGuess(),
                round.getResult());

        // Retrieve the last inserted ID
        String selectLastIdQuery = "CALL IDENTITY()";
        int roundId = jdbcTemplate.queryForObject(selectLastIdQuery, Integer.class);

        // Set the round ID
        round.setRoundId(roundId);
        return round;
    }

    @Override
    public void updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET gameId = ?, guessTime = ?, guess = ?, result = ? WHERE roundId = ?";
        jdbcTemplate.update(UPDATE_ROUND,
                round.getGameId(),
                round.getTimestamp(),
                round.getGuess(),
                round.getResult(),
                round.getRoundId());
    }

    @Override
    public void deleteRoundById(int id) {

        final String DELETE_ROUND = "DELETE FROM round WHERE roundId=?";
        jdbcTemplate.update(DELETE_ROUND, id);

    }

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round Round = new Round();
            Round.setRoundId(rs.getInt("roundId"));
            Round.setGameId(rs.getInt("gameId"));
            Round.setTimestamp(rs.getTimestamp("timestamp"));
            Round.setGuess(rs.getString("guess"));
            Round.setResult(rs.getString("result"));
            return Round;
        }
    }
}
