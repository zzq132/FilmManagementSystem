package Prj;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class ListAllSlotsAction extends AbstractAction{
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    ListAllSlotsAction(FilmSlotService filmSlotService, GlobalState state){
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "LIST_ALL_FILM_SLOTS";
    }

    public void run(){
        if(state.getIsAuthenticated() && (state.getUserRole().equals(Role.Manager)|| state.getUserRole().equals(Role.Receptionist))){
            HashMap<String,LinkedList<FilmSlot>> hashMap=filmSlotService.getAllFilmSlots();
            if(hashMap.isEmpty()){
                super.println("No slots.");
                return;
            }
            for(HashMap.Entry<String,LinkedList<FilmSlot>> entry:hashMap.entrySet()){
                String name=entry.getKey();
                super.println("Film: "+name);
                LinkedList<FilmSlot> linkedList=entry.getValue();
                Iterator<FilmSlot> iterator=linkedList.iterator();
                while(iterator.hasNext()){
                    FilmSlot filmSlot=iterator.next();
                    super.println("\nScreening Room: "+filmSlot.getScreeningRoom()+"\n"+"Period: "+filmSlot.getPeriod()+"\n"+"Price: "+filmSlot.getPrice());
                }
                System.out.println("");
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
