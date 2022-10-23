package view;

import view.admin.MenuStaffLogin;
import java.util.Scanner;

public class MainMenu extends MenuBase {

    public MainMenu(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("======================= MOBLIMA APP =======================\n" +
                    " 1. Customer App                                          \n" +
                    " 2. Staff App                                             \n" +
                    " 3. Quit App                                              \n" +
                    "===========================================================");

            System.out.println("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // Remove newline character
            }
            choice = sc.nextInt();
            // this means THIS object
            MenuBase nextMenu = this;

            switch (choice) {
                case 1:
                    // nextMenu = new Customer(this);
                    break;
                case 2:
                    nextMenu = new MenuStaffLogin(this);
                    break;
                case 3:
                    nextMenu = new Quit(this);
                    System.out.println("Thank you for using our MOBLIMA APP");
                    break;
                default:
                    System.out.println("Please enter an option between 0-2");
                    break;
            }
            return nextMenu;
        } while (choice != 0);


    }
}
