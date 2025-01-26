package Prj;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ListSlotAction extends AbstractAction{
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    ListSlotAction(FilmSlotService filmSlotService, GlobalState state){
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "LIST_SPECIFIC_FILM_SLOTS";
    }

    public void run(){
        if(state.getIsAuthenticated() && !state.getUserRole().equals(Role.Administrator)){
            super.print("Please enter film name: ");
            String filmName=scanner.nextLine();
            HashMap<String,LinkedList<FilmSlot>> hashMap=filmSlotService.getAllFilmSlots();
            LinkedList<FilmSlot> linkedList=hashMap.get(filmName);
            if(linkedList.isEmpty()){
                super.println("No slot.");
                return;
            }
            super.println("Film: "+filmName);
            Iterator<FilmSlot> iterator=linkedList.iterator();
            while(iterator.hasNext()){
                FilmSlot filmSlot=iterator.next();
                super.println("\nScreening Room: "+filmSlot.getScreeningRoom()+"\n"+"Period: "+filmSlot.getPeriod()+"\n"+"Price: "+filmSlot.getPrice());
            }
            super.println("List successfully!");
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(state.getUserRole().equals(Role.Administrator)){
                super.println("Changing failed: You're not manager, receptionist or customer.");
            }
        }
    }
}
