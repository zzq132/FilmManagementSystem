package Prj;

import java.util.Random;

public class FilmTicket {
    private String ID=null;
    private String time=null;
    private FilmSlot filmSlot=null;

    FilmTicket(FilmSlot filmSlot){
        this.filmSlot=filmSlot;
    }

    public void setTicketID(){
        Random random=new Random();
        int a=random.nextInt(100,1000);
        int b=random.nextInt(100, 1000);
        ID=Integer.toString(a)+Integer.toString(b);
    }

    public void setBuyingTime(String time){
        this.time=time;
    }

    public String getTicketID(){
        return ID;
    }

    public void getTicketInfo(User user){
        String filmName=filmSlot.film.getFilmName();
        String screeningRoom=filmSlot.getScreeningRoom();
        String period=filmSlot.getPeriod();
        String price=filmSlot.getPrice();
        String userName=user.getUserName();
        System.out.println("Ticket information:");
        System.out.println("Ticket ID: "+ID);
        System.out.println("User Name: "+userName);
        System.out.println("Film Name: "+filmName);
        System.out.println("Screening Room: "+screeningRoom);
        System.out.println("Period: "+period);
        System.out.println("Price: "+price);
    }

    public String getBuyingTime(){
        return time;
    }
}
