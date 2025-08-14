package respository;

import Modal.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDb {
    HashMap<Integer, User> userMap;
    public UserDb(){
        this.userMap= new HashMap<>();
    }
    public User getUserById(int id){
        return userMap.get(id);
    }
    public User createNewUser(String name){
        User user = new User();
        user.setName(name);
        int size = userMap.size();
        int id = size+1;
        user.setId(size);
        user.setGame(new ArrayList<>());
        userMap.put(id, user);
        return user;
    }
}
