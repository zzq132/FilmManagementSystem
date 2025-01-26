package Prj;

public class FilmSlot {
    private String period=null;
    private String price=null;
    private String screeningRoom=null;
    private String[][] seat=new String[7][12];
    Film film=null;

    FilmSlot(Film film){
        this.film=film;
        for(int i=0;i<7;i++){
            for(int j=0;j<12;j++){
                seat[i][j]="O";
            }
        }
    }

    public void setPeriod(String period){
        this.period=period;
    }

    public String getPeriod(){
        return period;
    }

    public void setPrice(String price){
        this.price=price;
    }

    public String getPrice(){
        return price;
    }

    public void setScreeningRoom(String screeningRoom){
        this.screeningRoom=screeningRoom;
    }

    public String getScreeningRoom(){
        return screeningRoom;
    }

    public void setSeat(int row, int column){
        seat[row-1][column-1]="X";
    }

    public String[][] getSeat(){
        return seat;
    }
}
