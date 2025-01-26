package Prj;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class RegisterAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    RegisterAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "REGISTERATION";
    }

    public void run(){
        super.print("UserName: ");
        String userName=scanner.nextLine();
        User user0=userService.getUser(userName);
        while(user0!=null){
            super.println("This user name has already existed, please change a user name.");
            super.print("UserName: ");
            userName=scanner.nextLine();
            user0=userService.getUser(userName);
        }
        super.print("Email Address: ");
        String address=scanner.nextLine();
        super.print("Password: ");
        String password=scanner.nextLine();
        super.print("Password Again: ");
        String password1=scanner.nextLine();
        if(password.equals(password1)){
            super.println("Registeration succeeded.");
        }
        else{
            super.println("Registeration failed: Passwords are different)");
        }

        User user=new User();
        user.setUsername(userName);
        user.setEmailAddress(address);
        user.setPassword(password);

        if(state.getUserRole()==null || state.getUserRole()==Role.Customer)
        user.setUserRole(Role.Customer);
        if(state.getUserRole()==Role.Administrator)
        user.setUserRole(Role.Administrator);
        if(state.getUserRole()==Role.Manager)
        user.setUserRole(Role.Manager);
        if(state.getUserRole()==Role.Receptionist)
        state.setUserRole(Role.Receptionist);

        userService.addUser(user);
    }
}
