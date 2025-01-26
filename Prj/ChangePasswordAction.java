package Prj;

import java.util.Scanner;

public class ChangePasswordAction extends AbstractAction{
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;

    ChangePasswordAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "CHANGE_PASSWORD";
    }

    public void run(){
        if(state.getIsAuthenticated()){
            super.print("Please enter new password: ");
            String newPassword=scanner.nextLine();
            User user=userService.getUser(state.getUserName());
            if(user.getPassword().equals(newPassword)){
                super.println("Chaning password failed: The new password is identical to the old password.");
            } else{
                user.setPassword(newPassword);
                super.println("Password of user "+user.getUserName() +" changed.");
            }
        } else{
            super.println("Authentication failed: You must be authenticated to perform this action.");
        }
    }
}
