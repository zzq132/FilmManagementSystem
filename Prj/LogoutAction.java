package Prj;

public class LogoutAction extends AbstractAction{
    GlobalState state=null;

    LogoutAction(GlobalState state){
        this.state=state;
    }

    public String getActionName(){
        return "LOGOUT";
    }

    public void run(){
        if(state.getIsAuthenticated()){
        state.setIsAuthenticated(false);
        state.setUserRole(null);
        state.setUsername(null);
        super.println("Logout successfully");
        } else{
            super.println("Authentication failed: You must be authenticated to perform this action.");
        }
    }
}
