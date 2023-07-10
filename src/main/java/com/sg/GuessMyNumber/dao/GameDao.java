package com.sg.GuessMyNumber.dao;

import com.sg.GuessMyNumber.dto.Game;

import java.util.List;

public interface GameDao {
    List<Game> getAllGames();
    Game getGameById(int id);
    Game addGame(Game game);
    void updateGame(Game game);
    void deleteGameById(int id);
}
