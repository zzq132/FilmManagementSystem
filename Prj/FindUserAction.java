package Prj;

import java.util.Scanner;
import Prj.AuthenticatedAction.Role;

public class FindUserAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    FindUserAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "FIND_USER";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Administrator)){
            super.print("Please enter the name of the user you want to find: ");
            String name=scanner.nextLine();
            if(userService.getUser(name)==null){
                super.println("This user doesn't exist.");
                return ;
            } else{
                super.println("User information:\n"+
                "User Name: "+state.getUserName()+
                "\nUser Password: "+userService.getUser(name).getPassword());
            }
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
