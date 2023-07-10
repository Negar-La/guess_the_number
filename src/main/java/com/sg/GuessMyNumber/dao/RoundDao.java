package com.sg.GuessMyNumber.dao;


import com.sg.GuessMyNumber.dto.Round;

import java.util.List;

public interface RoundDao {
    List<Round> getAllRounds();
    Round getRoundById(int id);
    Round addRound(Round round);
    void updateRound(Round round);
    void deleteRoundById(int id);
}
