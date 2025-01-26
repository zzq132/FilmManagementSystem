package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class GetTicketAction extends AbstractAction{
    UserService userService=null;
    FilmTicketService filmTicketService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    GetTicketAction(UserService userService,FilmTicketService filmTicketService, GlobalState state){
        this.filmTicketService=filmTicketService;
        this.userService=userService;
        this.state=state;
    }

    public String getActionName(){
        return "GET_TICKET";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Customer)){
            super.print("Please enter your ticket ID: ");
            String ID=scanner.nextLine();
            FilmTicket filmTicket=filmTicketService.getTicket(ID);
            if(filmTicket==null){
                super.println("Ticket don't enist.(It may have already been taken)");
            }else{
                super.println("Successful ticket collection.");
                filmTicketService.deleteTicket(userService.getUser(state.getUserName()));
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
