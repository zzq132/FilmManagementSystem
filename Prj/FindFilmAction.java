package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class FindFilmAction extends AbstractAction{
    GlobalState state=null;
    FilmService filmService=null;
    Scanner scanner=new Scanner(System.in);

    FindFilmAction(FilmService filmService, GlobalState state){
        this.filmService=filmService;
        this.state=state;
    }

    public String getActionName(){
        return "FIND_FILM";
    }

    public void run(){
        if(state.getUserRole().equals(Role.Manager) && state.getIsAuthenticated()){
            super.print("Please enter the information about the film you want to find(Format:filmName+\" \"+director+\" \"+actors): ");
            String info=scanner.nextLine();
            Film film=filmService.getFilmMixed(info);
            if(film!=null){
            super.println("There are the information about the film you find:");
            super.println("1.Film name: "+film.getFilmName());
            super.println("2.Director: "+film.getDirector());
            super.println("3.Actors: "+film.getActors());
            super.println("4.Introduction: "+film.getIntroduction());
            super.println("5.Time: "+film.getTime());
            } else{
                super.println("Failed to find this film.");
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Manager)){
                super.println("Changing failed: You're not manager.");
            }
        }
    }
}
