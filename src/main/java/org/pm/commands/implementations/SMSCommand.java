package org.pm.commands.implementations;

import org.pm.game.User;

public class SMSCommand {

    NotificationDetails notificationDetails;
     String link;
     String template;

    public SMSCommand(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public User getUser() {
        return notificationDetails.getUser();
    }

    public String getMessage() {
        return notificationDetails.getMessage();
    }
}
