package view.admin;

import view.MenuBase;
import view.Quit;

import java.util.Scanner;

public class MenuStaffMain extends MenuBase {
    public MenuStaffMain(MenuBase initialMenu){
        super(initialMenu);
    }

    public MenuBase execute(){
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Staff Menu");

        System.out.println("======================= Staff Menu =======================\n" +
                " 1. List Movies (STAFF)                                        \n" +
                " 2. Add Movie                                            \n" +
                " 3. Configure                                             \n" +
                " 4. Back                                                   \n" +
                "===========================================================");

        System.out.println("Enter choice: ");

        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // Remove newline character
        }
        choice = sc.nextInt();
        MenuBase nextMenu = this;
        switch (choice) {
            case 1:
                // List movies (STAFF)
                // nextMenu = new MovieListing(this);

                break;
            case 2:
                //
                break;
            case 3:

                break;
            default:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;
    }
}
