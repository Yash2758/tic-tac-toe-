package Modal;

import java.util.List;

public class User {
    int id;
    String name;
    List<Game> game;

    public User(int id, String name, List<Game> game) {
        this.id = id;
        this.name = name;
        this.game = game;
    }
    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }
}
