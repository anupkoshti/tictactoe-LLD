package org.pm.events;

import org.pm.game.User;

public class WinEvent extends Event {
    public WinEvent(User user) {
        super(user, "Congratulations on the win!", "https://www.google.com", "WIN");
    }
}
