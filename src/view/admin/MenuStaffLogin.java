package view.admin;

import controller.AdminController;
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
        AdminController adminCtrl = new AdminController();

        System.out.println(adminCtrl.readByUsername(username));
        if(username.equals(adminCtrl.readByUsername(username)) && password.equals(adminCtrl.readByPassword(password))){
            MenuStaffMain msm = new MenuStaffMain(this.getPreviousMenu());
            return msm;
        }else {
            System.out.println("Wrong username or password");
            return this.getPreviousMenu();
        }
    }
}
