package org.pm.api;

import org.pm.boards.CellBoard;
import org.pm.game.Board;
import org.pm.game.GameState;

import java.util.function.Function;

public class Rule {
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
