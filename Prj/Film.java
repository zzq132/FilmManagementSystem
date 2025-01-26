package Prj;

public class Film {
    private String filmName=null;
    private String director=null;
    private String actors=null;
    private String introduction=null;
    private String time=null;

    public String getFilmInfo(){
        return filmName+" "+director+" "+actors;
    }

    public void setFilmName(String name){
        filmName=name;
    }

    public String getFilmName(){
        return filmName;
    }

    public void setDirector(String director){
        this.director=director;
    }

    public String getDirector(){
        return director;
    }

    public void setActors(String actors){
        this.actors=actors;
    }

    public String getActors(){
        return actors;
    }

    public void setIntroduction(String intro){
        introduction=intro;
    }

    public String getIntroduction(){
        return introduction;
    }

    public void setTime(String time){
        this.time=time;
    }

    public String getTime(){
        return time;
    }
}
