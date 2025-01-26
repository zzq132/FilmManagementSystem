package Prj;

import java.util.LinkedList;

import Prj.AuthenticatedAction.Role;

public class ListFilmAction extends AbstractAction{
    FilmService filmService=null;
    GlobalState state=null;

    ListFilmAction(FilmService filmService,GlobalState state){
        this.filmService=filmService;
        this.state=state;
    }

    public String getActionName(){
        return"LIST_FILM_INFORMATION";
    }

    public void run(){
        if(!state.getUserRole().equals(Role.Administrator) && state.getIsAuthenticated()){
            LinkedList<Film> list=new LinkedList<>();
            list=filmService.getAllFilms();
            if(list.isEmpty()){
                super.println("No films.");
                return;
            }
            Film film=null;
            int i=1;
            while(!list.isEmpty()){    
                film=list.remove();
                super.println(i+":Film name: "+film.getFilmName());
                super.println("  Director: "+film.getDirector());
                super.println("  Actors: "+film.getActors());
                super.println("  Introduction: "+film.getIntroduction());
                super.println("  Time: "+film.getTime());
                i++;
            }
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
