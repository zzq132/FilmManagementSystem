package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class AddSlotAction extends AbstractAction{
    FilmService filmService=null;
    FilmSlotService filmSlotService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    AddSlotAction(FilmService filmService,FilmSlotService filmSlotService,GlobalState state){
        this.filmService=filmService;
        this.filmSlotService=filmSlotService;
        this.state=state;
    }

    public String getActionName(){
        return "ADD_FILM_SLOT";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            super.print("Please enter the name of the film which you want to add a scene to: ");
            String filmName=scanner.nextLine();
            if(filmService.getFilm(filmName)==null){
                super.println("Film doesn't exist.");
                return;
            }
            super.print("Please enter the period of time: ");
            String time=scanner.nextLine();
            super.print("Please enter the screening room: ");
            String screeningRome=scanner.nextLine();
            super.print("Please enter the price: ");
            String price=scanner.nextLine();         
            FilmSlot filmSlot=new FilmSlot(filmService.getFilm(filmName));
            filmSlot.setPeriod(time);
            filmSlot.setPrice(price);
            filmSlot.setScreeningRoom(screeningRome);
            filmSlotService.addFilmSlot(filmSlot);
            super.println("Add successfully!");
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
