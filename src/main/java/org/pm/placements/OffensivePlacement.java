package org.pm.placements;

import org.pm.api.RuleEngine;
import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Move;
import org.pm.game.Player;
import org.pm.utils.Utils;

import java.util.Optional;

public class OffensivePlacement implements Placement {
    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement() {

    }

    public static synchronized OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, () -> new OffensivePlacement());
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = offense(player, board);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }
    private static Cell offense(Player computer, TicTacToeBoard ticTacToeBoard) {
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
}
