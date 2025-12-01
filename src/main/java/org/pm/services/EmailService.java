package org.pm.services;

import org.pm.commands.implementations.EmailCommand;
import org.pm.game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        //todo: mail is sent
    }

    public void send(EmailCommand sendEmailCommand) {
        sendEmail(sendEmailCommand.getUser(), sendEmailCommand.getMessage() );
    }
}
