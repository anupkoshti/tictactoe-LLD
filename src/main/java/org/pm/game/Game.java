package org.pm.game;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    private Player winner;

    private int lastMoveTimeInMilliSec;
    private int maxTimePerPlayer;
    private int maxTimePerMove;

    public Game(GameConfig gameConfig, Board board, Player winner, int lastMoveTimeInMilliSec, int maxTimePerPlayer, int maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMilliSec = lastMoveTimeInMilliSec;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timeInMilliSec) {
        int timeTakenSinceLastMove = timeInMilliSec - lastMoveTimeInMilliSec;
        move.getPlayer().setTimeTaken(timeInMilliSec - lastMoveTimeInMilliSec);
        if(gameConfig.timed) {
            //if time per move is enabled
            moveForTimedGame(move, timeTakenSinceLastMove);
        }
        else {
            board.move(move);
        }
    }
    private void moveForTimedGame (Move move, int timeTakenSinceLastMove) {
        final int currentTime = gameConfig.timePerMove != null ? timeTakenSinceLastMove : move.getPlayer().getTimeUsedInMilliSec();
        final int endTime = gameConfig.timePerMove != null ? maxTimePerMove : maxTimePerPlayer;

        if(currentTime < endTime){
            board.move(move);
        }
        else {
            winner = move.getPlayer().flip();
        }
    }

    public void setConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }
}
