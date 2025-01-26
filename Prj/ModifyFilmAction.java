package Prj;

import java.util.Scanner;
import Prj.AuthenticatedAction.Role;

public class ModifyFilmAction extends AbstractAction {
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    ModifyFilmAction(FilmService filmService,GlobalState state){
        this.filmService=filmService;
        this.state=state;
    }

    public String getActionName(){
        return"MODIFY_FILM_INFORMATION";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            super.print("Please enter the name of the film you want to modify: ");
            String name=scanner.nextLine();
            Film film=filmService.getFilm(name);
            if(film==null){
                super.println("No film.");
                return;
            }
            super.println("These are all information concerning this film:");
            super.println("1.Film name: "+film.getFilmName());
            super.println("2.Director: "+film.getDirector());
            super.println("3.Actors: "+film.getActors());
            super.println("4.Introduction: "+film.getIntroduction());
            super.println("5.Time: "+film.getTime());
            super.print("Please enter the number of the information you want to modify: ");
            String info=scanner.nextLine();
            super.print("Please enter new information: ");
            String newInfo=scanner.nextLine();
            switch(info){
                case "1": film.setFilmName(newInfo);
                          super.println("Modify successfully!");
                          break;
                case "2": film.setDirector(newInfo);
                          super.println("Modify successfully!");
                          break;
                case "3": film.setActors(newInfo);
                          super.println("Modify successfully!");
                          break;
                case "4": film.setIntroduction(newInfo);
                          super.println("Modify successfully!");
                          break;
                case "5": film.setTime(newInfo);
                          super.println("Modify successfully!");
                          break;
                default : super.println("Failed!");
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Manager)){
                super.println("Modifying failed: You're not manager.");
            }
        }
    }
}
