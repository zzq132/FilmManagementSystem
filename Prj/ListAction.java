package Prj;

import java.util.List;

public class ListAction extends AbstractAction{
    List<AbstractAction> list;

    ListAction(List<AbstractAction>list){
        this.list=list;
    }

    public String getActionName(){
        return "LIST";
    }

    public void run(){
        for(AbstractAction action:this.list){
            super.println(action.getActionName());
        }
    }
}
