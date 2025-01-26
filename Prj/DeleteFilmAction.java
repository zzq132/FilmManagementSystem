package Prj;

import java.util.Scanner;

import Prj.AuthenticatedAction.Role;;

public class DeleteFilmAction extends AbstractAction{
    FilmService filmService=null;
    GlobalState state=null;
    Scanner scanner=new Scanner(System.in);

    DeleteFilmAction(FilmService filmService,GlobalState state){
        this.filmService=filmService;
        this.state=state;
    }

    public String getActionName(){
        return "DELETE_FILM";
    }

    public void run(){
        if(state.getIsAuthenticated() && state.getUserRole().equals(Role.Manager)){
            super.print("Please enter the name of the film you want to delete: ");
            String name=scanner.nextLine();
            if(filmService.getFilm(name)==null){
                super.println("This film doesn't exist.");
                return ;
            } else{
                super.print("Warning: Are you sure to delete this film? (y/n): ");
                String judge=scanner.nextLine();
                if(judge.equalsIgnoreCase("y")){
                    filmService.deleteFilm(name);
                    super.println("Delete successfully.");
                }
                if(judge.equalsIgnoreCase("n")){
                    super.println("Action is canceled.");
                    return ;
                }
            }
        }else{
            super.println("Failed");
            if(!state.getIsAuthenticated()){
                super.println("Authentication failed: You must be authenticated to perform this action.");
            }
            if(!state.getUserRole().equals(Role.Manager)){
                super.println("Deleting failed: You're not manager.");
            }
        }
    }
}
