package org.pm.events;

import org.pm.game.User;

public class ActivityEvent extends Event {
    public ActivityEvent(User user) {
        super(user, "Congratulations! We're glad you're back!", "https://www.google.com", "ACTIVITY");
    }
}
