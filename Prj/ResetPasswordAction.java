package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ResetPasswordAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    ResetPasswordAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "RESET_PASSWORD";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Administrator)){
            super.println("Please enter the name of the user you want to reset its password: ");
            String name=scanner.nextLine();
            User user=userService.getUser(name);
            user.setPassword("123456");
            super.println("Password reset successed. The new password is: 123456");
            return ;
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Administrator)){
                super.println("Finding failed: You're not administrator.");
            }
        } 
    }
}
