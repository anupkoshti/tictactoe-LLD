package org.pm;

import org.pm.api.*;
import org.pm.commands.builder.EmailCommandBuilder;
import org.pm.commands.builder.SMSCommandBuilder;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Move;
import org.pm.game.Player;
import org.pm.services.EmailService;
import org.pm.services.SMSService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();


        Board board = gameEngine.start("TicTacToe");
        Player computer = new Player("0"), human = new Player("X");

        if(human.getUser().isActiveAfter(10, TimeUnit.DAYS)) {
            emailService.send(
                    new EmailCommandBuilder()
                        .user(computer.getUser())
                        .message("We are glad you are back")
                        .link("https://www.youtube.com")
                        .build());
            smsService.send(
                    new SMSCommandBuilder()
                            .user(computer.getUser())
                            .message("We are glad you are back")
                            .build());
        };
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move:");
            System.out.println(board);

            int row = scanner.nextInt(),  col = scanner.nextInt();
            Move humanMove = new Move(new Cell(row, col), human);

            gameEngine.move(board, humanMove);

            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggesetMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
        if(ruleEngine.getState(board).getWinner().equals(human.symbol())) {
            emailService.send(
                    new EmailCommandBuilder()
                        .user(computer.getUser())
                        .message("Congratulations on the win!")
                        .build());
        }
        System.out.println("GameResult: "+ ruleEngine.getState(board));
        System.out.println(board);
    }
}
