package Prj;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import Prj.AuthenticatedAction.Role;

public class Main{
    private List<AbstractAction> newUserActions = null;
    private List<AbstractAction> administratorActions = null;
    private List<AbstractAction> managerActions = null;
    private List<AbstractAction> receptionistActions = null;
    private List<AbstractAction> customerActions = null;
    private Scanner scanner=null;
    private GlobalState globalState = null;
    private UserService userService = null;
    private FilmService filmService=null;
    private FilmSlotService filmSlotService=null;
    private FilmTicketService filmTicketService=null;

    public static void main(String[] args) {
        Main system=new Main();
        system.initialize();
        system.run();
        system.free();
    }

    protected void initialize(){
        System.out.println("Initializing...");

        newUserActions=new LinkedList<>();
        administratorActions=new LinkedList<>();
        managerActions=new LinkedList<>();
        receptionistActions=new LinkedList<>();
        scanner=new Scanner(System.in);
        customerActions=new LinkedList<>();
        globalState=new GlobalState();
        printStatus("Initialize global variables", "OK");

        userService=new UserService(new StorageUserDAO());
        filmService=new FilmService(new StorageFilmDAO());
        filmSlotService=new FilmSlotService(new StorageFilmSlotDAO());
        filmTicketService=new FilmTicketService(new StorageFilmTicketDAO());
        printStatus("Initialize storage space", "OK");

        newUserActions.add(new RegisterAction(userService,globalState));
        newUserActions.add(new LoginAction(userService, globalState));
        newUserActions.add(new LogoutAction(globalState));
        newUserActions.add(new ForgetPasswordAction(userService, globalState));
        newUserActions.add(new QuitAction(globalState));
        newUserActions.add(new ListAction(newUserActions));
        printStatus("Initialize new user actions", "OK");

        administratorActions.add(new RegisterAction(userService,globalState));
        administratorActions.add(new LoginAction(userService, globalState));
        administratorActions.add(new LogoutAction(globalState));
        administratorActions.add(new QuitAction(globalState));
        administratorActions.add((new ChangePasswordAction(userService, globalState)));
        administratorActions.add(new ChangeUserRoleAction(userService, globalState));
        administratorActions.add(new DeleteUserAction(userService, globalState));
        administratorActions.add(new FindUserAction(userService, globalState));
        administratorActions.add(new ListUserAction(userService, globalState));
        administratorActions.add(new ListAction(administratorActions));
        User administrator=new User();
        administrator.setUsername("admin");
        administrator.setPassword("ynuinfo#777");
        administrator.setUserRole(Role.Administrator);
        userService.addUser(administrator);
        printStatus("Initialize administrator actions", "OK");

        managerActions.add(new RegisterAction(userService,globalState));
        managerActions.add(new ChangePasswordAction(userService, globalState));
        managerActions.add(new LoginAction(userService, globalState));
        managerActions.add(new LogoutAction(globalState));
        managerActions.add(new QuitAction(globalState));
        managerActions.add(new ListAction(managerActions));
        managerActions.add(new ListFilmAction(filmService, globalState));
        managerActions.add(new AddFilmAction(filmService, globalState));
        managerActions.add(new ModifyFilmAction(filmService, globalState));
        managerActions.add(new DeleteFilmAction(filmService, globalState));
        managerActions.add(new FindFilmAction(filmService, globalState));
        managerActions.add(new AddSlotAction(filmService, filmSlotService, globalState));
        managerActions.add(new ModifySlotAction(filmSlotService, globalState));
        managerActions.add(new DeleteSlotAction(filmSlotService, globalState));
        managerActions.add(new ListAllSlotsAction(filmSlotService, globalState));
        printStatus("Initialize manager actions", "OK");

        receptionistActions.add(new RegisterAction(userService,globalState));
        receptionistActions.add(new ChangePasswordAction(userService, globalState));
        receptionistActions.add(new ListFilmAction(filmService, globalState));
        receptionistActions.add(new ListSlotAction(filmSlotService, globalState));
        receptionistActions.add(new ListSeatAction(filmSlotService, globalState));
        receptionistActions.add(new SellTicketAction(userService, filmTicketService, filmSlotService, globalState));
        receptionistActions.add(new LoginAction(userService, globalState));
        receptionistActions.add(new LogoutAction(globalState));
        receptionistActions.add(new QuitAction(globalState));
        receptionistActions.add(new ListAction(receptionistActions));
        printStatus("Initialize receptionist actions", "OK");

        customerActions.add(new RegisterAction(userService,globalState));
        customerActions.add(new ChangePasswordAction(userService, globalState));
        customerActions.add(new ForgetPasswordAction(userService, globalState));
        customerActions.add(new ListFilmAction(filmService, globalState));
        customerActions.add(new ListSlotAction(filmSlotService, globalState));
        customerActions.add(new BuyTicketAction(userService, filmTicketService, filmSlotService, globalState));
        customerActions.add(new GetTicketAction(userService, filmTicketService, globalState));
        customerActions.add(new CheckTicketAction(userService, filmTicketService, globalState));
        customerActions.add(new LoginAction(userService, globalState));
        customerActions.add(new LogoutAction(globalState));
        customerActions.add(new QuitAction(globalState));
        customerActions.add(new ListAction(customerActions));
        printStatus("Initialize customer actions", "OK");

        System.out.println("Initialization finished.");
        
    }

    protected void run(){
        System.out.println("Running...");
        System.out.println("Welcome to the film system!");
        System.out.println("Type 'LIST' to list all available actions you can perform.");

        while(globalState.getIsRunning()){
            String userName=globalState.getUserName();
            userName=(userName!=null)?("Logged in as: "+userName+"@"+globalState.getUserRole().toString()):"";
            System.out.print(userName+" >");

            String actionName=scanner.nextLine();
            Role role=globalState.getUserRole();
            tag:
            while(role==null){
                boolean flag=false;
                for(AbstractAction action:newUserActions){
                    if(action.getActionName().equals(actionName)){
                        action.run();
                        flag=true;
                        break tag;
                    }    
                }
                if(!flag){
                    System.out.println("Action not found.");
                    actionName=scanner.nextLine();
                }
            }
            if(role==null) continue;

            if(role.equals(Role.Administrator)){
                boolean flag=false;
                for(AbstractAction action:administratorActions){
                    if(action.getActionName().equals(actionName)){
                        action.run();
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                        System.out.println("Action not found.");
                }
            }

            if(role.equals(Role.Manager)){
                boolean flag=false;
                for(AbstractAction action:managerActions){
                    if(action.getActionName().equals(actionName)){
                        action.run();
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                        System.out.println("Action not found.");
                }
            }

            if(role.equals(Role.Receptionist)){
                boolean flag=false;
                for(AbstractAction action:receptionistActions){
                    if(action.getActionName().equals(actionName)){
                        action.run();
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                        System.out.println("Action not found.");
                }
            }

            if(role.equals(Role.Customer)){
                boolean flag=false;
                for(AbstractAction action:customerActions){
                    if(action.getActionName().equals(actionName)){
                        action.run();
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                        System.out.println("Action not found.");
                }
            }
        }
        System.out.println("Running finished.");
    }

    protected void free(){
        System.out.println("Freeing resources...");

        if (this.scanner != null) {
            this.scanner.close();
            this.printStatus("Close Scanner", "OK");
        }

        System.out.println("Resources freed.");
    }
    
    private void printStatus(String ifo, String status) {
        int totalLength = 80;
        int msgLength = ifo.length();
        int statusLength = status.length();
        int dotsLength = totalLength - msgLength - statusLength - 4; 

        if (msgLength > 70 || statusLength > 70) {
            throw new IllegalArgumentException("msg or status length exceeds 70 characters");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ifo);
        sb.append(" ");

        for (int i = 0; i < dotsLength; i++) {
            sb.append("·");
        }

        sb.append(" [");
        sb.append(status);
        sb.append("]");

        String result = sb.toString();
        System.out.println(result);
    }
}