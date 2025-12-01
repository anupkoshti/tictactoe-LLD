package org.pm.boards;

import org.pm.game.Board;

public interface CellBoard extends Board {
    String getSymbol(int i, int j);
}
