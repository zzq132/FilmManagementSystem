package Prj;
import java.util.Scanner;

public class GreetingAction extends AbstractAction {
    Scanner scanner=new Scanner(System.in);

    @Override
    public void run() {
        super.println("Hello! What should I call you?");
        String str=scanner.nextLine();
        super.println("Oh, hello "+str);
    }

    @Override
    public String getActionName() {
        return"HELLO";
    }
    
}
