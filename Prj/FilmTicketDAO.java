package Prj;

import java.util.LinkedList;

public interface FilmTicketDAO {
    void addTicket(User user,FilmTicket filmTicket);
    FilmTicket getTicket(String ID);
    LinkedList<FilmTicket> getUserAllTickets(User user);
    LinkedList<FilmTicket> getHistoryUserAllTickets(User user);
    void deleteTicket(User user);
}
