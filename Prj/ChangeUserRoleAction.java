package Prj;

import java.util.Scanner;
import Prj.AuthenticatedAction.Role;

public class ChangeUserRoleAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    ChangeUserRoleAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "CHANGE_ROLE";
    }

    public void run(){
        if(state.getUserRole().equals(Role.Administrator) && state.getIsAuthenticated()){
            super.print("Please enter the name of the user who you want to change its role: ");
            String userName=scanner.nextLine();
            User user=userService.getUser(userName);
            if(user==null){
                super.println("This user doesn't exist.");
                return;
            } else{
            super.println("\n1.Administrator\n2.Manager\n3.Receptionist\n4.Customer");
            super.print("Please enter the number of the role you want to change to: ");
            int index=scanner.nextInt();
            switch(index){
                case 1:user.setUserRole(Role.Administrator);super.println("Changing user role successfully.");
                break;
                case 2:user.setUserRole(Role.Manager);super.println("Changing user role successfully.");
                break;
                case 3:user.setUserRole(Role.Receptionist);super.println("Changing user role successfully.");
                break;
                case 4:user.setUserRole(Role.Customer);super.println("Changing user role successfully.");
                break;
                default:super.println("Failed: The number you have entered doesn't exit.");
            }
            }
        } else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Administrator)){
                super.println("Changing failed: You're not administrator.");
            }
        }
    }
}
