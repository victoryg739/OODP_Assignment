package view.admin;

import view.MenuBase;

import static view.utilF.*;

/**
 Menu to Configure holiday
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */

public class MenuConfigureHoliday extends MenuBase {
    public MenuConfigureHoliday(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Ask user to choice which holiday to configure
     * and bring user to next stage of application
     * Consist of four menu
     * 1. Add new Holiday
     * 2. Update Holiday
     * 3. Remove Holiday
     * 4. Quit
     *
     * @return return to the corresponding menu that the user has selected
     */

    public MenuBase execute() {
        int choice;
        MenuBase nextMenu = this;
        printHeader("Configure Holiday Settings");
        print("1.  Add new Holiday \n" +
                "2.  Update Holiday \n" +
                "3.  Remove Holiday  \n" +
                "4.  Back  \n");
        choice = readIntInput("Enter choice: ");

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
