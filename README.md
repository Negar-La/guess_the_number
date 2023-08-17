# Guess the Number (Bulls and Cows Game)

## Overview

Welcome to the exciting world of "Bulls and Cows"! This project involves building a Spring Boot REST application that brings the classic guessing game to life. In this game, players guess a secret 4-digit number, with each digit being distinct. The game provides feedback on both exact and partial matches, challenging players to decipher the correct sequence.

## Features

- **Interactive Gameplay**: Engage in an interactive gameplay experience where you attempt to guess the secret 4-digit number.
- **Feedback Mechanism**: Receive instant feedback on your guesses with both exact and partial match indications.
- **Dynamic Game Status**: Track the progress of each game, from guessing to victory, with updated status notifications.
- **User-Friendly Design**: Enjoy a user-friendly interface powered by Spring Boot REST technology.
- **RESTful Endpoints**: Explore a set of REST endpoints that drive the game's mechanics:
    - `POST /begin`: Start a new game, generate a secret answer, and set the game status.
    - `POST /guess`: Make guesses by submitting your guess and game ID in JSON format, and receive outcome updates.
    - `GET /game`: Retrieve an overview of all played games, excluding answers of ongoing ones.
    - `GET /game/{gameId}`: Access detailed information about a specific game by its ID, without revealing answers of ongoing games.
    - `GET /rounds/{gameId}`: View the chronological history of guesses for a chosen game.
- **Strategic Service Layer**: Utilize a dedicated service layer that manages game rules, ensuring fairness and engagement.
- **Rigorous Testing**: Benefit from comprehensive testing to ensure a smooth and optimized gaming experience.
- **Innovative Database Interaction**: Leverage JDBC Template for efficient and reliable interactions with the database.

