package view.admin;

import controller.*;
import view.MenuBase;
import static view.utilF.*;

public class MenuStaffMovieCreate extends MenuBase {

    public MenuStaffMovieCreate(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        AdminController adminC = new AdminController();
        System.out.println("Creating movie listing....");

        String title = read("Enter movie title: ");


        adminC.create(title);
        return this.getPreviousMenu();
    }
}
