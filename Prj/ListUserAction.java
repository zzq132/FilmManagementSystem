package Prj;

import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ListUserAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    ListUserAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "LIST_USER";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Administrator)){
            LinkedList<User> list=new LinkedList<User>();
            list=userService.getAllUser();
            if(list.isEmpty()){
                super.println("No user.");
                return;
            }
            while(!list.isEmpty()){
                User user=list.remove();
                super.println("User information:\n"+
                "User Name: "+user.getUserName()+
                "\nUser Password: "+user.getPassword()+
                "\nUser Role:"+user.getUserRole().toString()+
                "\nUser email:"+user.getEmailAddress());
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Administrator)){
                super.println("Listing failed: You're not administrator.");
            }
        }
    }
}
