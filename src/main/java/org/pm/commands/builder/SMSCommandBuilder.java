package org.pm.commands.builder;

import org.pm.commands.implementations.EmailCommand;
import org.pm.commands.implementations.SMSCommand;
import org.pm.game.User;

public class SMSCommandBuilder {

    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String template;

    public SMSCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public SMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }

    public SMSCommand build() {
        return new SMSCommand(notificationBuilder.build());
    }

}
