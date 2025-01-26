package Prj;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class CheckTicketAction extends AbstractAction{
    UserService userService=null;
    FilmTicketService filmTicketService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    CheckTicketAction(UserService userService,FilmTicketService filmTicketService,GlobalState state){
        this.filmTicketService=filmTicketService;
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "CHECK_TICKET";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Customer)){
            User user=userService.getUser(state.getUserName());
            LinkedList<FilmTicket> linkedList=filmTicketService.getHistoryUserAllTickets(user);
            if(linkedList==null){
                super.println("You haven't bought ticket yet.");
                return;
            }
            Iterator<FilmTicket> iterator=linkedList.iterator();
            super.println("This is your history of buying tickets.");
            while(iterator.hasNext()){
                FilmTicket filmTicket=iterator.next();
                super.println("Buying Time: "+filmTicket.getBuyingTime());
                filmTicket.getTicketInfo(user);
                System.out.println("");
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
