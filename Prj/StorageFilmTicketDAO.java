package Prj;

import java.util.HashMap;
import java.util.LinkedList;

public class StorageFilmTicketDAO implements FilmTicketDAO{
    HashMap<User,LinkedList<FilmTicket>> hashMap=new HashMap<>();
    HashMap<String,FilmTicket> hashMap2=new HashMap<>();
    HashMap<User,LinkedList<FilmTicket>> constHashMap=new HashMap<>();

    public void addTicket(User user,FilmTicket filmTicket){
        LinkedList<FilmTicket> linkedList=hashMap.get(user);
        if(linkedList==null){
            LinkedList<FilmTicket> linkedList1=new LinkedList<>();
            linkedList1.add(filmTicket);
            hashMap.put(user, linkedList1);
            constHashMap.put(user, linkedList);
            hashMap2.put(filmTicket.getTicketID(), filmTicket);
            return;
        }
        linkedList.add(filmTicket);
        hashMap.put(user, linkedList);
        constHashMap.put(user, linkedList);
        hashMap2.put(filmTicket.getTicketID(), filmTicket);
    }

    public FilmTicket getTicket(String ID){
        return hashMap2.get(ID);
    }

    public LinkedList<FilmTicket> getUserAllTickets(User user){
        return hashMap.get(user);
    }

    public LinkedList<FilmTicket> getHistoryUserAllTickets(User user){
        return constHashMap.get(user);
    }

    public void deleteTicket(User user){
        hashMap.remove(user);
    }
}
