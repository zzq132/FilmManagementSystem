package Prj;

import java.util.HashMap;
import java.util.LinkedList;

public class StorageFilmDAO implements FilmDAO{
    HashMap<String,Film> hashMap1=new HashMap<>();
    HashMap<String,Film> hashMap2=new HashMap<>();
    LinkedList<Film> list=new LinkedList<>();

    public void addFilm(Film film,String info){
        hashMap1.put(film.getFilmName(), film);
        hashMap2.put(info,film);
    }

    public Film getFilm(String name){
        return hashMap1.get(name);
    }

    public Film getFilmMixed(String info){
        for(String key:hashMap2.keySet()){
            if(key.contains(info)){
                return hashMap2.get(key);
            }
        }
        return null;
    }

    public void updateFilm(Film oldFilm,Film newFilm){
        hashMap1.replace(oldFilm.getFilmName(), newFilm);
    }

    public void deleteFilm(String name){
        hashMap1.remove(name);
    }

    public LinkedList<Film> getAllFilms(){
        for(Film film:hashMap1.values()){
            list.add(film);
        }
        return list;
    }
}
