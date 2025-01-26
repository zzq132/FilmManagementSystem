package Prj;
import Prj.AuthenticatedAction.Role;
public class User {
    private String userName=null;
    private String password=null;
    private String emailAddress=null;
    private Role role=null;

    public void setUsername(String userName){
        this.userName=userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }

    public void setUserRole(Role role){
        this.role=role;
    }

    public Role getUserRole(){
        return role;
    }

    public void setEmailAddress(String email){
        emailAddress=email;
    }

    public String getEmailAddress(){
        return emailAddress;
    }
}
