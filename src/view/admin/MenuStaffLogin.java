package view.admin;

import view.MenuBase;

import static view.utilF.read;

public class MenuStaffLogin extends MenuBase{
    public MenuStaffLogin(MenuBase initialMenu){
        super(initialMenu);
    }

    public MenuBase execute(){

        System.out.println("Please login using username and password");
        final String username = read("Username: ");
        final String password = read("Password: ");
        System.out.println("username is " + username + "password is " + password);

        if(username.equals("a") && password.equals("a")){
            MenuStaffMain msm = new MenuStaffMain(this.getPreviousMenu());
            return msm;
        }else {
            System.out.println("Wrong username or password");
            return this.getPreviousMenu();
        }
    }
}
