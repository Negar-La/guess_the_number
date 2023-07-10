package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.dto.Round;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoundDaoImpl implements RoundDao{
    @Override
    public List<Round> getAllRounds() {
        return null;
    }

    @Override
    public Round getRoundById(int id) {
        return null;
    }

    @Override
    public Round addRound(Round round) {
        return null;
    }

    @Override
    public void updateRound(Round round) {

    }

    @Override
    public void deleteRoundById(int id) {

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
