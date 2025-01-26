package Prj;

import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class DeleteSlotAction extends AbstractAction{
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    DeleteSlotAction(FilmSlotService filmSlotService, GlobalState state){
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "DELETE_FILM_SLOT";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            super.print("Please enter the film name: ");
            String name=scanner.nextLine();
            LinkedList<FilmSlot> linkedList=filmSlotService.getFilmSlots(name);
            super.print("Please enter some information concerning the slot you wnat to delete.");
            super.print("Please enter the screening room：");
            String screeningRoom=scanner.nextLine();
            super.print("Please enter original period of time: ");
            String time=scanner.nextLine();
            FilmSlot filmSlot=filmSlotService.geFilmSlot(linkedList, screeningRoom, time);
            filmSlotService.deleteFilmSlot(filmSlot);
            super.println("Delete successfully!");
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
