package org.pm.api;

import org.pm.boards.TicTacToeBoard;
import org.pm.game.*;
import org.pm.placements.OffensivePlacement;
import org.pm.placements.Placement;

import java.util.Optional;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggesetMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Cell suggestion;
            if(isStarting(ticTacToeBoard, 2)) {
                suggestion = getBasicMove(ticTacToeBoard);
            }
            else if(isStarting(ticTacToeBoard, 3)) {
                suggestion = getCellToPlay(computer, ticTacToeBoard);
            }
            else {
                suggestion = getOptimalMove(computer,ticTacToeBoard);
            }
            if (suggestion != null) return new Move(suggestion, computer);
            throw new IllegalStateException();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private Cell getOptimalMove(Player computer, TicTacToeBoard ticTacToeBoard) {

        //1. if you have winning move play it

        Placement placement = OffensivePlacement.get();

        while (placement.next() != null) {
            Optional<Cell> optionalCell = placement.place(ticTacToeBoard, computer);
            if (optionalCell.isPresent()) {
                return optionalCell.get();
            }
            placement=placement.next();
        }
        return null;
        //2. if opp has winning move, block it
        //3. if you have a fork then play it
        //4. if opp has fork, block it
        //5. if center is available take it
        //6. if corner is available take it

    }

    private Cell getCellToPlay(Player computer, TicTacToeBoard ticTacToeBoard) {

        RuleEngine ruleEngine = new RuleEngine();

        //attacking move
        //can computer win by making the next move?? yes -> make that move
        Cell best = offense(computer, ticTacToeBoard);
        if (best != null) return best;

        //defensive move
        //can human win by making the next move?? yes -> block human
        best = defense(computer, ticTacToeBoard);
        if (best != null) return best;

        return getFallbackMove(computer, ticTacToeBoard);
    }
    private static Cell defense(Player computer, TicTacToeBoard ticTacToeBoard) {
        RuleEngine ruleEngine = new RuleEngine();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(ticTacToeBoard.getSymbol(i,j) == null) {
                    Move move = new Move(new Cell(i,j), computer.flip());
                    TicTacToeBoard boardCopy = (TicTacToeBoard) ticTacToeBoard.move(move);
//                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }

    private static Cell offense(Player computer, TicTacToeBoard ticTacToeBoard) {
        RuleEngine ruleEngine = new RuleEngine();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(ticTacToeBoard.getSymbol(i,j) == null) {
                    Move move = new Move(new Cell(i,j), computer);
                    TicTacToeBoard boardCopy = (TicTacToeBoard) ticTacToeBoard.move(move);
//                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private boolean isStarting(TicTacToeBoard ticTacToeBoard, int threshold) {
        int counter = 0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (ticTacToeBoard.getSymbol(i,j)!=null) {
                    counter++;
                }
            }
        }
        return counter < threshold;
    }

    private Cell getBasicMove(TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(i,j) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    private Cell getFallbackMove(Player computer, TicTacToeBoard board) {

        // 1. center
        if (board.getSymbol(1, 1) == null) {
            return new Cell(1, 1);
        }

        // 2. corners
        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        for (int[] c : corners) {
            if (board.getSymbol(c[0], c[1]) == null) {
                return new Cell(c[0], c[1]);
            }
        }

        // 3. edges
        int[][] edges = {{0,1},{1,0},{1,2},{2,1}};
        for (int[] e : edges) {
            if (board.getSymbol(e[0], e[1]) == null) {
                return new Cell(e[0], e[1]);
            }
        }

        return null; // should never happen
    }

}
