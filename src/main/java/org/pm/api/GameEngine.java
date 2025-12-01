package org.pm.api;


import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Move;

public class GameEngine {


    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    public Board move(Board board, Move move) {
        if (board instanceof TicTacToeBoard) {
            return board.move(move);
        }
        else {
            throw new IllegalArgumentException();
        }
    }


}
