package org.pm.placements;

import org.pm.api.RuleEngine;
import org.pm.boards.TicTacToeBoard;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Player;

import java.util.Optional;

public interface Placement {

    RuleEngine ruleEngine = new RuleEngine();

    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placement next();
}
