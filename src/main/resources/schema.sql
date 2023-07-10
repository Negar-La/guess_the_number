DROP DATABASE IF EXISTS guessNumber;
CREATE DATABASE guessNumber;

USE guessNumber;

CREATE TABLE game(
    gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer VARCHAR(4) NOT NULL,
    isFinished BOOLEAN DEFAULT false
);

CREATE TABLE round(
    roundId INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4) NOT NULL,
    guessTime DATETIME NOT NULL,
    result VARCHAR(10) NOT NULL,
    gameId INT NOT NULL,
    FOREIGN KEY (gameId) REFERENCES game(gameId)
);
