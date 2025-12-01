package org.pm.placements;

import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Player;
import org.pm.utils.Utils;

import java.util.Optional;

public class CentrePlacement implements Placement {
    private static CentrePlacement centrePlacement;

    private CentrePlacement() {

    }

    public static synchronized CentrePlacement get() {
        centrePlacement = (CentrePlacement) Utils.getIfNull(centrePlacement, () -> new CentrePlacement());
        return centrePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell centre = null;
        if(board.getSymbol(1,1) == null) {
            centre = new Cell(1,1);
        }
        return Optional.ofNullable(centre);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
