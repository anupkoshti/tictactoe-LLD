package org.pm.game;

import org.pm.boards.TicTacToeBoard;

public interface Board {
    Board move(Move move);

    TicTacToeBoard copy();

}

