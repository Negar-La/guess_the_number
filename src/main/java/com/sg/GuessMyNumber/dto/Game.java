package com.sg.GuessMyNumber.dto;

public class Game {
    private int gameId;
    private String answer;
    private boolean isFinished;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", answer='" + answer + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (gameId != game.gameId) return false;
        if (isFinished != game.isFinished) return false;
        return answer.equals(game.answer);
    }

    @Override
    public int hashCode() {
        int result = gameId;
        result = 31 * result + answer.hashCode();
        result = 31 * result + (isFinished ? 1 : 0);
        return result;
    }
}
