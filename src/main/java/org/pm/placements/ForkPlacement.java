package org.pm.placements;

import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.GameInfo;
import org.pm.game.Player;
import org.pm.utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    private ForkPlacement() {

    }

    public static synchronized ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, () -> new ForkPlacement());
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }
    @Override
    public Placement next() {
        return CentrePlacement.get();
    }
}
