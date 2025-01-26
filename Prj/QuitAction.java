package Prj;

import java.util.Scanner;

public class QuitAction extends AbstractAction{
    Scanner scanner=new Scanner(System.in);
    GlobalState state=null;

    QuitAction(GlobalState state){
        this.state=state;
    }

    public String getActionName(){
        return "QUIT";
    }

    public void run(){
        super.print("Are you sure to quit the system? (y/n): ");
        String judge=scanner.nextLine();
        if(judge.equalsIgnoreCase("y")){
            state.setIsRunning(false);
        }
    }
}
