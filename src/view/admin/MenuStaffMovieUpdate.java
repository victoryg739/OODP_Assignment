package view.admin;

import controller.MovieController;
import view.MenuBase;

import static view.utilF.*;

public class MenuStaffMovieUpdate extends MenuBase {
    public MenuStaffMovieUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    MovieController mController = new MovieController();
    MenuStaffMovieList listMenu = new MenuStaffMovieList(this);
    public MenuBase execute() {
        MenuBase nextMenu = this;
        boolean stay = true;
        System.out.println("Updating movie...");
        listMenu.execute();
        System.out.println("Select movie to be updated: ");

        int movieID = readIntInput("Enter move ID: ");
        System.out.println("Select movie attribute to update");;
        int choice = readIntInput(  "1. Title\n" +
                "2. Type\n" +
                "3. Synopsis\n" +
                "4. Rating\n" +
                "5. Duration \n" +
                "6. Movie Release date\n" +
                "7. End of Showing date\n" +
                "8. Director\n" +
                "9. Cast\n\n" +
                "Enter option: ");

        switch(choice){
            case 1:
                String title = read("Enter New Title:");
                mController.updateMovie(1, movieID, title);
                System.out.println("Succesfull! title updated");
                break;
        }

        return this.getPreviousMenu();
    }
}
