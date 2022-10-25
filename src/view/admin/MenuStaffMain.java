package view.admin;

import view.MenuBase;

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
                " 1. Create/Update/Remove Listing                                     \n" +
                " 2. Create/Update/Remove Session                                            \n" +
                " 3. Configure system settings                                          \n" +
                " 4. List movies                                                   \n" +
                " 5. Back                                                           \n " +
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
                nextMenu = new MenuStaffMovieListFunction(this);

                break;
            case 2:
                //
                break;
            case 3:

                break;
            case 4:
                nextMenu = new MenuStaffMovieList(this);
                break;
            default:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;
    }
}
