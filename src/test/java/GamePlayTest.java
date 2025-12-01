import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pm.api.GameEngine;
import org.pm.api.RuleEngine;
import org.pm.game.Board;
import org.pm.game.Cell;
import org.pm.game.Move;
import org.pm.game.Player;


public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = {{1,0}, {1, 1}, {1,2}};
        int[][] cMoves = {{0,0}, {2, 1}, {2, 2}};
        board = playGame(board, moves, cMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = {{0,0}, {1, 0}, {2,0}};
        int[][] cMoves = {{0,1}, {2, 1}, {2, 2}};
        board = playGame(board, moves, cMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = {{0,0}, {1, 1}, {2,2}};
        int[][] cMoves = {{0,1}, {2, 1}, {2, 0}};
        board = playGame(board, moves, cMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForReverseDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = {{0,2}, {1, 1}, {2,0}};
        int[][] cMoves = {{0,0}, {2, 1}, {2, 2}};
        board = playGame(board, moves, cMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = {{1,0}, {1, 1}, {2,0}};
        int[][] cMoves = {{0,0}, {0,1}, {0,2}};
        board = playGame(board, moves, cMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("0", ruleEngine.getState(board).getWinner());
    }

    private Board playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int row, col;
        int next=0;
        while (!ruleEngine.getState(board).isOver() && next < firstPlayerMoves.length) {
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Player computer = new Player("0"), human = new Player("X");
            Move humanMove = new Move(new Cell(row, col), human);
            board = gameEngine.move(board, humanMove);
            if(!ruleEngine.getState(board).isOver() && next < secondPlayerMoves.length) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move computerMove = new Move(new Cell(sRow, sCol), computer);
                board = gameEngine.move(board, computerMove);
            }
            next++;
        }
        return board;
    }
}
