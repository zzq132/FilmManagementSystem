package Prj;

public class AuthenticatedAction {
    public enum Role{Administrator,Manager,Receptionist,Customer;}

    public static Role currentRole=null;
    public static boolean isAuthenticated=true;

    public void valid() throws AuthenticationException{
        if(!isAuthenticated){throw new AuthenticationException("You must be authenticated to perform this action.");}
    }

    public void setUserRole(Role role){
        currentRole=role;
    }

    public void setIsAuthenticated(boolean b){
        isAuthenticated=b;
    }

    public Role getUserRole(){
        return currentRole;
    }

    public boolean getIsAuthenticated(){
        return isAuthenticated;
    }
}

class AuthenticationException extends Exception{
    AuthenticationException(String str){
        System.out.println(str);
    }
}
