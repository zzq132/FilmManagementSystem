package Prj;

import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ListSeatAction extends AbstractAction{
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    ListSeatAction(FilmSlotService filmSlotService, GlobalState state){
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "LIST_SEAT";
    }

    public void run(){
        if(state.getIsAuthenticated() && (state.getUserRole().equals(Role.Receptionist)||state.getUserRole().equals(Role.Customer))){
            super.print("Please enter the film name: ");
            String name=scanner.nextLine();
            LinkedList<FilmSlot> linkedList=filmSlotService.getFilmSlots(name);
            super.print("Please enter the screening room：");
            String screeningRoom=scanner.nextLine();
            super.print("Please enter period of time: ");
            String time=scanner.nextLine();
            FilmSlot filmSlot=filmSlotService.geFilmSlot(linkedList, screeningRoom, time);
            if(filmSlot==null){
                super.println("The slot doesn't exist.");
                return ;
            }
            String[][] seat=filmSlot.getSeat();
            super.println("This is the information of the seat: ");
            for(int i=0;i<7;i++){
                if(i==0){
                    System.out.print("\t");
                    for(int p=1;p<=12;p++){
                        print(p+"\t");
                    }
                    System.out.println("");
                }
                System.out.print((i+1)+"\t");
                for(int j=0;i<12;j++){
                    System.out.print(seat[i][j]+"\t");
                }
                System.out.println("");
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!(state.getUserRole().equals(Role.Receptionist)||state.getUserRole().equals(Role.Customer))){
                super.println("Adding failed: You're not receptionist or customer.");
            }
        }
    }
}
