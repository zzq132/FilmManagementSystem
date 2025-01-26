package Prj;

import java.util.HashMap;
import java.util.LinkedList;

public class StorageUserDAO implements UserDAO{
    public HashMap<String,User> hashMap=new HashMap<>();

    public void addUser(User user){
        hashMap.put(user.getUserName(), user);
    }

    public User getUser(String userName){
        return hashMap.get(userName);
    }

    public void updateUser(User oldUser,User newUser){
        hashMap.replace(oldUser.getUserName(), newUser);
    }

    public void deleteUser(String userName){
        hashMap.remove(userName);
    }

    public LinkedList<User> getAllUsers(){
        LinkedList<User> list=new LinkedList<>();
        for(User user:hashMap.values()){
            list.add(user);
        }
        return list;
    }
}
