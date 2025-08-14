package Modal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {
    int id;
    LocalDateTime startingTime;
    User user1;
    User user2;
    User Winner;
    Board board;

//    public Game(LocalDateTime startingTime, int id, User user1, User user2, User winner, Board board) {
//        this.startingTime = startingTime;
//        this.id = id;
//        this.user1 = user1;
//        this.user2 = user2;
//        Winner = winner;
//        this.board = board;
//    }
    public Game() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getWinner() {
        return Winner;
    }

    public void setWinner(User winner) {
        Winner = winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

