package org.pm.api;

import org.pm.boards.CellBoard;
import org.pm.boards.TicTacToeBoard;
import org.pm.game.*;
import org.pm.placements.DefensivePlacement;
import org.pm.placements.OffensivePlacement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.pm.boards.TicTacToeBoard.*;


public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState((TicTacToeBoard) board);
            //detect fork in game
            for(Symbol symbol: Symbol.values()){
                Player player = new Player(symbol.getMarker());
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(ticTacToeBoard.getSymbol(i,j) != null) {
                            TicTacToeBoard b = (TicTacToeBoard) ticTacToeBoard.move(new Move(new Cell(i, j), player));
                            //force opp to make a defensive move
                            //we still after that move
                            DefensivePlacement defense = DefensivePlacement.get();
                            Optional<Cell> defensiveCell = defense.place(b, player.flip());
                            if(defensiveCell.isPresent()) {
                                b = (TicTacToeBoard) b.move(new Move(new Cell(i, j), player));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offensiveCell = offense.place(b, player);
                                if(offensiveCell.isPresent()) {
                                    return new GameInfoBuilder()
                                        .isOver(gameState.isOver())
                                        .winner(gameState.getWinner())
                                        .hasFork(true)
                                        .forkCell(new Cell(i, j))
                                        .player(player.flip())
                                        .build();
                                }
                            }
                        }
                    }
                }
            }
                return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }


    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), getRules());

    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (Rule rule :  ruleMap.get(TicTacToeBoard.class.getName())) {
                GameState gameState = rule.condition.apply(ticTacToeBoard);
                if (gameState != null && gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException();
        }
    }

//            //1. check row complete
//            GameState rowWin = outerTraversal((i, j) -> ticTacToeBoard.getSymbol(i,j));
//            if (rowWin.isOver()) return rowWin;
//            //2. check col complete
//            GameState colWin = outerTraversal((i, j) -> ticTacToeBoard.getSymbol(j,i));
//            if (colWin.isOver()) return colWin;
//            //3. check diagonal complete i,i
//            GameState diagWin = traverse(i -> ticTacToeBoard.getSymbol(i,i));
//            if (diagWin.isOver()) return diagWin;
//            //4. check reverse diagonal complete i,i
//            GameState revDiagWin = traverse(i -> ticTacToeBoard.getSymbol(i,2-i));
//            if (revDiagWin.isOver()) return revDiagWin;

            //5.
//            int count = 0;
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (ticTacToeBoard.getSymbol(i,j) != null) {
//                        count++;
//                    }
//                }
//            }
//            if (count == 9) {
//                return new GameState(true, "-");
//            }

}


