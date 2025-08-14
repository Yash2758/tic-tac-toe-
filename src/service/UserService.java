package service;

import Modal.User;
import respository.UserDb;

public class UserService {
    UserDb userDb;
    public UserService() {
        this.userDb = new UserDb();
    }
    public User validateUserId(int id){
        return this.userDb.getUserById(id);
    }
    public User createNewUser(String username){
        return userDb.createNewUser(username);
    }
}
