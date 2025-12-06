package org.pm.commands.implementations;

import org.pm.events.Event;
import org.pm.game.User;

public class EmailCommand  {
    NotificationDetails notificationDetails;
     String link;
     String template;

    public EmailCommand(Event event) {
        this.notificationDetails = new NotificationDetails(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }

    // Constructor for builder pattern
    public EmailCommand(NotificationDetails notificationDetails, String link) {
        this.notificationDetails = notificationDetails;
        this.link = link;
    }

    public User getUser() {
        return notificationDetails.getUser();
    }

    public String getMessage() {
        return notificationDetails.getMessage();
    }
}
