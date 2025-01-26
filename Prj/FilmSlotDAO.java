package Prj;

import java.util.HashMap;
import java.util.LinkedList;

public interface FilmSlotDAO {
    void addFilmSlot(FilmSlot filmSlot);
    HashMap<String,LinkedList<FilmSlot>> getAllFilmSlots();
    LinkedList<FilmSlot> getFilmSlots(String name);
    FilmSlot getFilmSlot(LinkedList<FilmSlot> list,String screeningRoom,String period);
    void deleteFilmSlot(FilmSlot filmSlot);
}
