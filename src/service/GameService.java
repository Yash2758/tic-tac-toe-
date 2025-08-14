package service;

import Modal.Board;
import Modal.Game;
import Modal.User;
import respository.GameDb;

import java.util.Scanner;

public class GameService {

    GameDb gameDB;

    public GameService() {
        this.gameDB = new GameDb();
    }

    public Game createGame(User p1, User p2) {
        return gameDB.createGame(p1, p2);
    }

    public void startGame(Game game) {
        Scanner sc = new Scanner(System.in);
        User player1 = game.getUser1();
        User player2 = game.getUser2();
        Board board = game.getBoard();
        User currPlayer = player1;
        char ch = 'x';
        int count = 0;
        while (count < 9) {
            System.out.printf("%s this is your turn%n", currPlayer.getName());
            System.out.println("Enter your row number");
            int rowNo = sc.nextInt();
            System.out.println("Enter your col number");
            int colNo = sc.nextInt();
            if(ch == 'x') {
                board.getRows()[rowNo]++;
                if (board.getRows()[rowNo] == 3) {
                    System.out.printf("%s won the game", currPlayer.getName());
                    return;
                }
                board.getCols()[colNo]++;
                if (board.getCols()[colNo] == 3) {
                    System.out.printf("%s won the game", currPlayer.getName());
                    return;
                }
                if (rowNo == colNo) {
                    board.setDig(board.getDig() + 1);
                }
                if (rowNo + colNo == 2) {
                    board.setAntiDig(board.getAntiDig() + 1);
                }
            }
            if(ch == 'o') {
                board.getRows()[rowNo]--;
                if (board.getRows()[rowNo] == -3) {
                    System.out.printf("%s won the game", currPlayer.getName());
                    return;
                }
                board.getCols()[colNo]--;
                if (board.getCols()[colNo] == -3) {
                    System.out.printf("%s won the game", currPlayer.getName());
                    return;
                }
                if (rowNo == colNo) {
                    board.setDig(board.getDig() + 1);
                }
                if (rowNo + colNo == 2) {
                    board.setAntiDig(board.getAntiDig() + 1);
                }
            }
            if (ch == 'x') {
                ch = 'o';
                currPlayer = player2;
            } else {
                ch = 'x';
                currPlayer = player1;
            }
            board.getBoard()[rowNo][count] = ch;
            board.displayBoard();
            count++;
        }
        System.out.println("Tie");
    }
}