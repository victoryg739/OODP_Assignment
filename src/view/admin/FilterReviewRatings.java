package view.admin;

import controller.SettingController;
import view.MenuBase;

import java.util.ArrayList;

import static view.utilF.print;
import static view.utilF.readIntInput;

public class FilterReviewRatings extends MenuBase {
    SettingController sc = new SettingController();

    public FilterReviewRatings(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        String variable = "ratings";
        ArrayList<String> allData = sc.readSettings();
        ArrayList<String> returnData = new ArrayList<>();
        ;
        int choice = readIntInput("Press 1 to enable Rating Display \n" +
                "Press 2 to disable Ticket Rating Display");

        if (choice == 1) { // Enable ticket Rating display
            // Check if it is already enabled
            if (sc.enableTicket(variable)) {
                print("Option already enabled");
            } else {
                allData.add(variable);
                print("Ratings Display enabled");
                sc.writeSetting(allData);
            }

        } else if (choice == 2) { // Disable ticket Rating display
            if (!sc.enableTicket(variable)) {
                print("Option already disabled");
            } else {
                for (String s : allData) {
                    if (!s.equals(variable)) {
                        returnData.add(s);
                    }
                }
                sc.replaceFile(returnData);
                print("Ticket Rating Display disabled");
            }
        }
        return this.getPreviousMenu();
    }
}

