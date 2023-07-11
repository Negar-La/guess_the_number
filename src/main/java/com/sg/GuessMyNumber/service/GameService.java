package com.sg.GuessMyNumber.service;

import com.sg.GuessMyNumber.dao.GameDao;
import com.sg.GuessMyNumber.dto.Game;
import com.sg.GuessMyNumber.dto.Round;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
@Service
public class GameService {

    public Game newGame() {

        // Creates game and initializes random number generator
        Game game = new Game();
        Random rand = new Random();

        // Create stringBuilder to hold random number (use stringBuilder because we can modify its contents without building a new object)
        StringBuilder answer = new StringBuilder();
        Set<Integer> usedDigits = new HashSet<>();

        // generate 4 digit random number
        while (usedDigits.size() < 4) {
            int num = rand.nextInt(10);
            if (!usedDigits.contains(num)) {  // check to make sure all digits are unique
                usedDigits.add(num);
                answer.append(num);
            }
        }
        // set answer and defaults status is false
        game.setAnswer(answer.toString());
        game.isFinished();
        return game;
    }

    public Game getGames(Game game) {
        if (!game.isFinished()) {
            game.setAnswer("****");
        }
        return game;
    }

    public void getAllGames(List<Game> games) {
        for (Game game : games) {
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }
    }

    public Round guessNumber(Game game, String guess, GameDao gameDao) {
        Round round = checkGuess(game, guess);
        if (game.isFinished()) {
            gameDao.updateGame(game);
        }
        setTimeStamp(round);
        round.setGameId(game.getGameId());

        return round;
    }


    // checks guess and sets the result
    public Round checkGuess(Game game, String guess) {
        Round round = new Round();
        round.setGuess(guess);

        int partial = 0;
        int exact = 0;
        String answer = game.getAnswer();

        if (guess.equals(answer)) {
            game.setFinished(true);
        }

        String resultsFormat = "e:%d:p:%d";

        // if guess is not the expected length then return 0 matches
        if (guess.length() != 4) {
            round.setResult(String.format(resultsFormat, exact, partial));
            return round;
        }

        for (int i = 0; i < 4; i++) {
            String guessLetter = String.valueOf(guess.charAt(i));
            if (guess.charAt(i) == answer.charAt(i)) {
                exact += 1;
            } else if (answer.contains(guessLetter)) {
                partial += 1;
            }
        }

        round.setResult(String.format(resultsFormat, exact, partial));
        return round;
    }

    public void setTimeStamp(Round round) {
        Calendar calendar = Calendar.getInstance();
        Timestamp guessTime = new Timestamp(calendar.getTime().getTime());

        round.setTimestamp(guessTime);
    }










}
