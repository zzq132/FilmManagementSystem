package Prj;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Prj.AuthenticatedAction.Role;

public class BuyTicketAction extends AbstractAction{
    UserService userService=null;
    FilmTicketService filmTicketService=null;
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);
    int row,colume;

    BuyTicketAction(UserService userService,FilmTicketService filmTicketService,FilmSlotService filmSlotService, GlobalState state){
        this.filmTicketService=filmTicketService;
        this.userService=userService;
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "BUY_TICKET";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Customer)){
            super.print("Please enter the film name: ");
            String name=scanner.nextLine();
            LinkedList<FilmSlot> linkedList=filmSlotService.getFilmSlots(name);
            super.print("Please enter the screening room：");
            String screeningRoom=scanner.nextLine();
            super.print("Please enter period of time: ");
            String time=scanner.nextLine();
            FilmSlot filmSlot=filmSlotService.geFilmSlot(linkedList, screeningRoom, time);
            if(filmSlot==null){
                super.println("No slot.");
                return;
            }
            String[][] seat=filmSlot.getSeat();
            super.println("This is the information of the seat: ");
            for(int i=0;i<7;i++){
                if(i==0){
                    System.out.print("\t");
                    for(int p=1;p<=12;p++){
                        System.out.print(p+"\t");
                    }
                    System.out.println("");
                }
                System.out.print((i+1)+"\t");
                for(int j=0;j<12;j++){
                    System.out.print(seat[i][j]+"\t");
                }
                System.out.println("");
            }
            super.println("Please entr the number of the seat you want to choose.");
            super.print("row: ");
            row=scanner.nextInt();
            super.print("colume: ");
            colume=scanner.nextInt();
            while(seat[row-1][colume-1].equals("X")){
                super.println("This seat has already been chosen, you should choose another one.");
                super.print("row: ");
                row=scanner.nextInt();
                super.print("colume: ");
                colume=scanner.nextInt();
            }
            filmSlot.setSeat(row, colume);
            super.println("Please pay for the film ticket within two minutes, otherwise your seat will be released.");
            Timer timer=new Timer();
            TimerTask task = new TimerTask() {
            public void run() {
                seat[row-1][colume-1]="O";
                System.out.println("Your seat is released.");
            }
        };
            timer.schedule(task, 120*1000);
            super.print("Payment(Enter 'y' to indicate payment, and 'n' to indicate non-payment.): ");
            scanner.nextLine();
            String tag=scanner.nextLine();
            if(tag.equalsIgnoreCase("y")){
                timer.cancel();
                FilmTicket filmTicket=new FilmTicket(filmSlot);
                filmTicket.setTicketID();
                Date date=new Date();
                SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                filmTicket.setBuyingTime(ft.format(date));
                filmTicketService.addTicket(userService.getUser(state.getUserName()), filmTicket);
                super.println("Payment success!");
                super.println("This is your ticket ID: "+filmTicket.getTicketID());
            }
            if(tag.equalsIgnoreCase("n")){
                super.println("Payment failuer!");
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Customer)){
                super.println("Changing failed: You're not customer.");
            }
        }
    }
}
