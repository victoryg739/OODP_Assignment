package view.admin;
import view.MenuBase;;
import static view.utilF.*;

/**
 Menu to Configure for admin to configure settings
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */
public class MenuStaffConfigureSettings extends MenuBase {
    public MenuStaffConfigureSettings(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Display options that Admin can configure system settings
     * Ask user which settings admin want to configure
     * and bring user to next stage of application
     * Consist of four menu
     * 1. Configure Price
     * 2. Holiday
     * 3. Configure admin account
     * 4. Configure Moviegoer's display info
     * 5. Back
     *
     * @return return to the corresponding menu that the user has selected
     */

    public MenuBase execute() {
        int choice;
        MenuBase nextMenu = this;
        printHeader("Configure System Settings");
        print("1. Configure Price \n" +
                "2. Configure Holiday \n" +
                "3. Configure admin account \n" +
                "4. Configure Moviegoer's display info\n" +
                "5. Back\n");
        choice = readIntInput("Enter Choice: ");

        switch (choice) {
            case 1:
                nextMenu = new MenuConfigurePrice(this);
                break;
            case 2:
                nextMenu = new MenuConfigureHoliday(this);
                break;
            case 3:
                nextMenu = new MenuStaffRegister(this);
                break;
            case 4:
                nextMenu = new MenuMovieGoerDisplay(this);
                break;
            case 5:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }
}
