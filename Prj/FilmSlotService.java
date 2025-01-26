package Prj;

import java.util.HashMap;
import java.util.LinkedList;

public class FilmSlotService {
    FilmSlotDAO filmSlotDAO=null;

    FilmSlotService(FilmSlotDAO filmSlotDAO){
        this.filmSlotDAO=filmSlotDAO;
    }

    void addFilmSlot(FilmSlot filmSlot){
        filmSlotDAO.addFilmSlot(filmSlot);
    }

    HashMap<String,LinkedList<FilmSlot>> getAllFilmSlots(){
        return filmSlotDAO.getAllFilmSlots();
    }

    LinkedList<FilmSlot> getFilmSlots(String filmName){
        return filmSlotDAO.getFilmSlots(filmName);
    }

    FilmSlot geFilmSlot(LinkedList<FilmSlot>list,String screeningRoom,String period){
        return filmSlotDAO.getFilmSlot(list, screeningRoom, period);
    }

    void deleteFilmSlot(FilmSlot filmSlot){
        filmSlotDAO.deleteFilmSlot(filmSlot);
    }

}
