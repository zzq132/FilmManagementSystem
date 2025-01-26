package Prj;

import java.util.LinkedList;

public interface FilmDAO {
    void addFilm(Film film,String info);
    Film getFilm(String name);
    Film getFilmMixed(String info);
    void updateFilm(Film oldFilm,Film newFilm);
    void deleteFilm(String name);
    LinkedList<Film> getAllFilms();
}
