package Prj;

import java.security.GeneralSecurityException;
import java.util.Random;
import java.util.Scanner;

public class ForgetPasswordAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    GlobalState state=null;

    ForgetPasswordAction(UserService userService,GlobalState state){
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "FORGET_PASSWORD";
    }

    public void run(){
        if(state.getIsAuthenticated()){
            super.print("Please enter your username: ");
            String name=scanner.nextLine();
            super.print("Please enter your email address which was used to register: ");
            String address=scanner.nextLine();
            Random random=new Random();
            int a=random.nextInt(10,101),b=random.nextInt(10);
            String password=Integer.toString(a*10+b)+(char)(random.nextInt(65, 91))+(char)(random.nextInt(97,123))+(char)(random.nextInt());
            SendEmail semail=new SendEmail();
            try{
                semail.sendEmail(address, password);
            } catch(GeneralSecurityException g){
                System.out.println("Failed: GeneralSecurityException");
            }
            User user=userService.getUser(name);
            user.setPassword(password);
            super.println("Password reset successfully.");
        }
    }
}
