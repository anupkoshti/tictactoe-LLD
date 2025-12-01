package org.pm.game;

import org.pm.boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return new Game(new GameConfig(maxTimePerPlayer != null, maxTimePerPlayer),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard startingBoard) {
        return new Game(new GameConfig(maxTimePerPlayer != null, maxTimePerPlayer),
                startingBoard,
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }
}
