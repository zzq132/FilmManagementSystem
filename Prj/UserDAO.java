package Prj;
import java.util.LinkedList;
public interface UserDAO {
    void addUser(User user);
    User getUser(String userName);
    void updateUser(User oldUser,User newUser);
    void deleteUser(String userName);
    LinkedList<User> getAllUsers();
}
