package org.pm.placements;

import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Player;
import org.pm.utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement {
    private static CornerPlacement cornerPlacement;

    private CornerPlacement() {

    }

    public static synchronized CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, () -> new CornerPlacement());
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell cornerCell=null;
        int[][] corners = new int[][] {{0,0},{0,2},{2,0},{2,2}};
        for (int[] corner : corners) {
            if (board.getSymbol(corner[0], corner[1]) == null) {
                cornerCell=new Cell(corner[0], corner[1]);
            }
        }
        return  Optional.ofNullable(cornerCell);
    }

    @Override
    public Placement next() {
        return null;
    }
}
