package Prj;

import java.util.Scanner;
import Prj.AuthenticatedAction.Role;;

public class DeleteUserAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    DeleteUserAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "DELETE_USER";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Administrator)){
            super.print("Please enter the name of the user you want to delete: ");
            String name=scanner.nextLine();
            if(userService.getUser(name)==null){
                super.println("This user doesn't exist.");
                return ;
            } else{
                super.print("Warning: Are you sure to delete this user? (y/n): ");
                String judge=scanner.nextLine();
                if(judge.equalsIgnoreCase("y")){
                    userService.deleteUser(name);
                    super.println("Delete successfully.");
                }
                if(judge.equalsIgnoreCase("n")){
                    super.println("Action is canceled.");
                    return ;
                }
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Administrator)){
                super.println("Deleting failed: You're not administrator.");
            }
        }
    }
}
