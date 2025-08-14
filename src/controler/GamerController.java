package controler;

import Modal.Game;
import Modal.User;
import service.GameService;
import service.UserService;

import java.util.Random;
import java.util.Scanner;

public class GamerController {

    UserService userService;
    GameService gameService;
    Scanner scn = new Scanner(System.in);

    public GamerController() {
        this.userService = new UserService();
        this.gameService = new GameService();
    }

    public int toss() {
        Random random = new Random();
        return Math.abs(random.nextInt()) % 2;
    }

    public User inputPlayerDetails(int playerNumber) {
        System.out.printf("Player%d: Are you a new user ?(Yes/No)", playerNumber);
        String userResp1 = scn.next();
        if (userResp1.equals("No")) {
            while (true) {
                System.out.printf("Player%d: Tell us your userId%n", playerNumber);
                int id = scn.nextInt();
                User user = userService.validateUserId(id);
                if (user != null) {
                    System.out.println("Correct id entered");
                    return user;
                }
                System.out.println("Player1: Wrong Id entered !!");
            }
        } else {
            System.out.printf("Player%d: Enter your name%n", playerNumber);
            String name = scn.next();
            return userService.createNewUser(name);
        }
    }

    public void startProgram() {
        System.out.println("Welcome to TicTacToe Game !!");
        System.out.println("Let's start game and complete your registration !!");
        User player1 = this.inputPlayerDetails(1);
        User player2 = this.inputPlayerDetails(2);
        System.out.println("Starting toss");
        Game game;
        int val = this.toss();
        if (val == 0) {
            System.out.printf("Congratulation %s you won the toss%n", player1.getName());
            game = this.gameService.createGame(player1, player2);
        } else {
            System.out.printf("Congratulations %s you won the toss%n", player2.getName());
            game = this.gameService.createGame(player2, player1);
        }

        this.gameService.startGame(game);
    }
}