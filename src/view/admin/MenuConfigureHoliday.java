package view.admin;

import view.MenuBase;

import static view.utilF.readIntInput;

public class MenuConfigureHoliday extends MenuBase {
    public MenuConfigureHoliday(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        int choice;
        MenuBase nextMenu = this;

        choice = readIntInput("\nConfigure Holiday Settings: \n" +
                "1.  Add new Holiday \n" +
                "2.  Update Holiday \n +" +
                "3.  Remove Holiday  \n + " +
                "4.  Back  \n");

        switch (choice) {
            case 1:
                nextMenu = new MenuHolidayCreate(this);
                break;
            case 2:
                nextMenu = new MenuHolidayUpdate(this);
                break;
            case 3:
                nextMenu = new MenuHolidayRemove(this);
                break;
            case 4:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }
}
