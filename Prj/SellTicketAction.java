package Prj;

import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class SellTicketAction extends AbstractAction{
    UserService userService=null;
    FilmTicketService filmTicketService=null;
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    SellTicketAction(UserService userService,FilmTicketService filmTicketService,FilmSlotService filmSlotService, GlobalState state){
        this.userService=userService;
        this.filmTicketService=filmTicketService;
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "SELL_TICKET";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Receptionist)){
            super.print("Please enter your user name: ");
            String userName=scanner.nextLine();
            super.print("Please enter the film name: ");
            String filmName=scanner.nextLine();
            LinkedList<FilmSlot> linkedList=filmSlotService.getFilmSlots(filmName);
            super.print("Please enter the screening room：");
            String screeningRoom=scanner.nextLine();
            super.print("Please enter the period of time: ");
            String time=scanner.nextLine();
            FilmSlot filmSlot=filmSlotService.geFilmSlot(linkedList, screeningRoom, time);
            FilmTicket filmTicket=new FilmTicket(filmSlot);
            filmTicket.setTicketID();
            filmTicketService.addTicket(userService.getUser(userName), filmTicket);
            filmTicket.getTicketInfo(userService.getUser(userName));
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Receptionist)){
                super.println("Adding failed: You're not receptionist.");
            }
        }
    }
}
