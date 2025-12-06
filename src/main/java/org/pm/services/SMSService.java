package org.pm.services;

import org.pm.commands.implementations.SMSCommand;
import org.pm.game.User;

public class SMSService {
    private void sendEmail(User user, String message) {
        //todo: mail is sent
    }

    public Void send(SMSCommand smsCommand) {
        sendEmail(smsCommand.getUser(), smsCommand.getMessage());
        return null;
    }
}
