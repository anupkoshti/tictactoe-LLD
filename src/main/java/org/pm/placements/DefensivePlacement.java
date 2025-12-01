package org.pm.placements;

import org.pm.api.RuleEngine;
import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Move;
import org.pm.game.Player;
import org.pm.utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement {
    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {

    }
    //dont implement singleton pattern by yourself -> cz of multiple threads
    // calling the methods
    //use libraries instead
    public static synchronized DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, () -> new DefensivePlacement());
        return defensivePlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = defense(player, board);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }



    private static Cell defense(Player computer, TicTacToeBoard ticTacToeBoard) {
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
}
