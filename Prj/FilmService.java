package Prj;

import java.util.LinkedList;

public class FilmService{
    FilmDAO filmDAO=null;

    FilmService(FilmDAO filmDAO){
        this.filmDAO=filmDAO;
    }

    void addFilm(Film film,String info){
        filmDAO.addFilm(film,info);
    }

    Film getFilm(String name){
        return filmDAO.getFilm(name);
    }

    Film getFilmMixed(String info){
        return filmDAO.getFilmMixed(info);
    }

    void updateFilm(Film oldFilm,Film newFilm){
        filmDAO.updateFilm(oldFilm, newFilm);
    }

    void deleteFilm(String name){
        filmDAO.deleteFilm(name);
    }
    
    LinkedList<Film> getAllFilms(){
        return filmDAO.getAllFilms();
    }
}
