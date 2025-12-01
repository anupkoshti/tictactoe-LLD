package org.pm.game;

public class GameConfig {
    public boolean timed;
    public Integer timePerMove;

    public GameConfig(boolean timed, Integer timePerMove) {
        this.timed = timed;
        this.timePerMove = timePerMove;
    }
}
