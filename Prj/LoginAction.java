package Prj;

import java.util.Scanner;
import Prj.AuthenticatedAction.Role;

public class LoginAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    LoginAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "LOGIN";
    }

    public void run(){
        super.print("UserName: ");
        String userName=scanner.nextLine();
        super.print("Password: ");
        String password=scanner.nextLine();
        User user=userService.getUser(userName);
        if(user==null){
            super.println("User doesn't exist.");
            return;
        }
        Role role=login(userName, password);
        if(user.getPassword().equals(password)){
            state.setUsername(userName);
            state.setUserRole(role);
            state.setIsAuthenticated(true);
            super.println("Login successfully");
            super.println("Welcome "+user.getUserName()+" !");
        }else{
            super.println("Login failed");
        }
    }

    public Role login(String userName,String password){
        if(userName.equals("admin") && password.equals("ynuinfo#777")){
            return Role.Administrator;
        }
        User user=userService.getUser(userName);
        if(user!=null && user.getPassword().equals(password)){
            return user.getUserRole();
        }
        return null;
    }
}
