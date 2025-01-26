package Prj;
import Prj.AuthenticatedAction.Role;
public class GlobalState {
    private boolean isRunning=true;
    private String userName=null;

    public void setIsRunning(boolean isRunning){
        this.isRunning=isRunning;
    }

    public void setUsername(String userName){
        this.userName=userName;
    }

    public void setUserRole(Role role){
        AuthenticatedAction.currentRole=role;
    }

    public void setIsAuthenticated(boolean b){
        AuthenticatedAction.isAuthenticated=b;
    }

    public boolean getIsRunning(){
        return isRunning;
    }

    public String getUserName(){
        return userName;
    }

    public Role getUserRole(){
        return AuthenticatedAction.currentRole;
    }

    public boolean getIsAuthenticated(){
        return AuthenticatedAction.isAuthenticated;
    }
}
