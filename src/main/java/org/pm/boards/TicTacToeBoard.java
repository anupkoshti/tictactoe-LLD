package org.pm.boards;

import org.pm.api.Rule;
import org.pm.api.RuleSet;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.GameState;
import org.pm.game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


public class TicTacToeBoard implements CellBoard {
    String[][] cells = new String[3][3];

    History history = new History();


    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(i,j))));
        rules.add(new Rule(board -> outerTraversal((i,j) -> (board).getSymbol(j,i))));
        rules.add(new Rule(board -> traverse(i -> (board).getSymbol(i,i))));
        rules.add(new Rule(board -> traverse(i -> (board).getSymbol(i,2-i))));
        rules.add(new Rule(board -> {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i,j) != null) {
                        count++;
                    }
                }
            }
            if (count == 9) {
                return new GameState(true, "-");
            }
            return null;
        }));
        return rules;
    }

    @Override
    public String getSymbol(int i, int j) {
        return cells[i][j];
    }

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += getSymbol(i, j) == null ? "- " : getSymbol(i, j) + " " ;
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public Board move(Move move) {
        history.add(getRepresentation());
        TicTacToeBoard ticTacToeBoard = copy();
        ticTacToeBoard.setCell(move.getCell(), move.getPlayer().symbol());
        return ticTacToeBoard;
    }

    private Representation getRepresentation() {
        return new Representation(this);
    }


    // prototype design pattern
    @Override
     public TicTacToeBoard copy() {
        TicTacToeBoard board = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.cells[i][j] = getSymbol(i, j);
            }
        }

        board.history = history;
//        for(Representation representation : this.history.boards) {
//            board.history.add(representation);
//        }
        return board;
    }

    private static GameState traverse(Function<Integer, String> diag) {
        GameState gameState = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (diag.apply(i) == null || !diag.apply(0).equals(diag.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak && diag.apply(0) != null) {
            gameState =  new GameState(true, diag.apply(0));
        }
        return gameState;
    }

    //    private final Function<TicTacToeBoard, GameState> outerTraversal = (board -> outerTraversal(board::getSymbol));
    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState gameState = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            GameState traversal = traverse(j -> next.apply(finalI,j));
            if (traversal.isOver()) {
                gameState = traversal;
                break;
            }
        }
        return gameState;
    }

    public enum Symbol {
        X("X"),
        O("O");
        final String marker;
        Symbol(String marker) {
            this.marker = marker;
        }
        public String getMarker() {
            return marker;
        }
    }
}

class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIdx) {
        moveIdx = moveIdx-1;
        int initialSize=boards.size();
        for (int i=0;i<initialSize - (moveIdx+1);i++) {
            boards.remove(boards.size()-1);
        }

        return boards.get(moveIdx);
    }

    public Representation undo() {
        boards.remove(boards.size()-1);
        return boards.get(boards.size() - 1);
    }

    public void add(Representation ticTacToeBoard) {
        boards.add(ticTacToeBoard);
    }
}


class Representation {
    String representation;

    public Representation(TicTacToeBoard ticTacToeBoard) {
        representation = ticTacToeBoard.toString();
    }
}