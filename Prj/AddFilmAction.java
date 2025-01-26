package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;

public class AddFilmAction extends AbstractAction {
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    AddFilmAction(FilmService filmService,GlobalState state){
        this.filmService=filmService;
        this.state=state;
    }

    public String getActionName(){
        return"ADD_FILM";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            Film film=new Film();
            super.print("Please enter the film's name: ");
            String filmName=scanner.nextLine();
            film.setFilmName(filmName);
            super.print("Please enter the director's name: ");
            String directorName=scanner.nextLine();
            film.setDirector(directorName);
            super.print("Please enter actors' names: ");
            String actorName=scanner.nextLine();
            film.setActors(actorName);
            super.print("Please enter the film's introduction: ");
            String introduction=scanner.nextLine();
            film.setIntroduction(introduction);
            super.print("Please enter the time of this film: ");
            String time=scanner.nextLine();
            film.setTime(time);
            String info=film.getFilmInfo();
            filmService.addFilm(film,info);
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
