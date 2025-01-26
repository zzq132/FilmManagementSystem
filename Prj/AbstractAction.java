package Prj;

public abstract class AbstractAction {
    public abstract void run();
    public abstract String getActionName();
    //public abstract String getInfo();

    public void print(Object o){
        System.out.print(getActionName().toUpperCase()+"> "+o);
    }

    public void println(Object o){
        System.out.println(getActionName().toUpperCase()+"> "+o);
    }
}
