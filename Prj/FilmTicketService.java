package Prj;

import java.util.LinkedList;

public class FilmTicketService {
    FilmTicketDAO filmTicketDAO=null;

    FilmTicketService(FilmTicketDAO filmTicketDAO){
        this.filmTicketDAO=filmTicketDAO;
    }

    public void addTicket(User user,FilmTicket filmTicket){
        filmTicketDAO.addTicket(user,filmTicket);
    }

    public FilmTicket getTicket(String ID){
        return filmTicketDAO.getTicket(ID);
    }

    public LinkedList<FilmTicket> getUserAllTicket(User user){
        return filmTicketDAO.getUserAllTickets(user);
    }

    public LinkedList<FilmTicket> getHistoryUserAllTickets(User user){
        return filmTicketDAO.getHistoryUserAllTickets(user);
    }

    public void deleteTicket(User user){
        filmTicketDAO.deleteTicket(user);
    }
}
