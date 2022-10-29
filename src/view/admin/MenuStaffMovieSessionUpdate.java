package view.admin;

import view.MenuBase;
public class MenuStaffMovieSessionUpdate extends MenuBase {

    public MenuStaffMovieSessionUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {

        return this.getPreviousMenu();
    }

}


