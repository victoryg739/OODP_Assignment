package view.admin;

import view.MenuBase;
public class MenuStaffMovieSessionRemove extends MenuBase {

    public MenuStaffMovieSessionRemove(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {

        return this.getPreviousMenu();
    }

}


