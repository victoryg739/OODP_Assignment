package view;

import static view.utilF.*;

public class EntryMenu extends MenuBase{
    public EntryMenu(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        printHeader("Login Menu");
        int choice = readIntInput("Do you wish to login to MOblima?\n (1) - Yes \n (2) - No");
        if(choice == 1){
            // Go to LoginMenu
        }
        // Go to MainMenu
        return null;
    }
}
