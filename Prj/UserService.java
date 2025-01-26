package Prj;

import java.util.LinkedList;

public class UserService {
    UserDAO userDAO;

    UserService(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public void addUser(User user){
        userDAO.addUser(user);
    }

    public User getUser(String userName){
        return userDAO.getUser(userName);
    }

    public LinkedList<User> getAllUser(){
        return userDAO.getAllUsers();
    }
    public void updateUser(User oldUser,User newUser){
        userDAO.updateUser(oldUser,newUser);
    }

    public void deleteUser(String userName){
        userDAO.deleteUser(userName);
    }
}
