package Prj;

import java.util.*;

public class StorageFilmSlotDAO implements FilmSlotDAO{
    HashMap<String,LinkedList<FilmSlot>> hashMap=new HashMap<>();
    LinkedList<FilmSlot> list=new LinkedList<>();

    public void addFilmSlot(FilmSlot filmSlot){
        list.add(filmSlot);
        hashMap.put(filmSlot.film.getFilmName(),list);
    }

    public HashMap<String,LinkedList<FilmSlot>> getAllFilmSlots(){
        return hashMap;
    }

    public LinkedList<FilmSlot> getFilmSlots(String filmName){
        return hashMap.get(filmName);
    }

    public FilmSlot getFilmSlot(LinkedList<FilmSlot> list, String screeningRoom, String period) {
        if(list.isEmpty()){
            return null;
        }
        Iterator<FilmSlot> iterator=list.iterator();
        while(iterator.hasNext()){
            FilmSlot filmSlot=iterator.next();
            if(filmSlot.getScreeningRoom().equals(screeningRoom) && filmSlot.getPeriod().equals(period)){
                return filmSlot;
            }
        }
        return null;
    }

    public void deleteFilmSlot(FilmSlot filmSlot){
        list.remove(filmSlot);
    }

}
