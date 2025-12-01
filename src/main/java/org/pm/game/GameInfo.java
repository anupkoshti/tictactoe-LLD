package org.pm.game;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private int noOfMoves;
    private Cell forkCell;

    public boolean hasFork() {
        return hasFork;
    }

    public Cell getForkCell() {
        return forkCell;
    }

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int noOfMoves, Cell forkCell) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.noOfMoves = noOfMoves;
        this.forkCell=forkCell;
    }
}


