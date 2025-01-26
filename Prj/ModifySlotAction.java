package Prj;

import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ModifySlotAction extends AbstractAction{
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    ModifySlotAction(FilmSlotService filmSlotService, GlobalState state){
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "MODIFY_FILM_SLOT";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            super.print("Please enter the name of the film which you want to modify its scene: ");
            String filmName=scanner.nextLine();
            LinkedList<FilmSlot> filmSlots=filmSlotService.getFilmSlots(filmName);
            super.print("Please enter some information concerning the slot you wnat to change.");
            super.print("Please enter original screening room：");
            String oscreeningRoom=scanner.nextLine();
            super.print("Please enter original period of time: ");
            String otime=scanner.nextLine();
            FilmSlot filmSlot=filmSlotService.geFilmSlot(filmSlots, oscreeningRoom, otime);
            super.print("Please enter new screening room: ");
            String screeningRoom=scanner.nextLine();
            filmSlot.setScreeningRoom(screeningRoom);
            super.print("Please enter new period of time: ");
            String time=scanner.nextLine();
            filmSlot.setPeriod(time);
            super.print("Please enter new price: ");
            String price=scanner.nextLine();
            filmSlot.setPrice(price);
            super.println("Modify successfully!");
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Manager)){
                super.println("Adding failed: You're not manager.");
            }
        }
    }
}
