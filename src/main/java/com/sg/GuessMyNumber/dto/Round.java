package com.sg.GuessMyNumber.dto;

import java.sql.Timestamp;

public class Round {
    private int roundId;
    private int gameId;
    private Timestamp timestamp;
    private String guess;
    private String result;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", gameId=" + gameId +
                ", timestamp=" + timestamp +
                ", guess='" + guess + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Round round = (Round) o;

        if (roundId != round.roundId) return false;
        if (gameId != round.gameId) return false;
        if (!timestamp.equals(round.timestamp)) return false;
        if (!guess.equals(round.guess)) return false;
        return result.equals(round.result);
    }

    @Override
    public int hashCode() {
        int result1 = roundId;
        result1 = 31 * result1 + gameId;
        result1 = 31 * result1 + timestamp.hashCode();
        result1 = 31 * result1 + guess.hashCode();
        result1 = 31 * result1 + result.hashCode();
        return result1;
    }
}
