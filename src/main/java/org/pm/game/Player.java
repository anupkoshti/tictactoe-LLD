package org.pm.game;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Player {

    private int timeUsedInMilliSec;

    private final User user;

    private final String playerSymbol;

    public Player(String playerSymbol) {
        this.user = new User();
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(Objects.equals(playerSymbol, "X") ? "0" :  playerSymbol);
    }

    public void setTimeTaken(int timeTaken) {
        this.timeUsedInMilliSec += timeTaken;
    }

    public int getTimeUsedInMilliSec() {
        return timeUsedInMilliSec;
    }

    public User getUser() {
        return user;
    }


}
