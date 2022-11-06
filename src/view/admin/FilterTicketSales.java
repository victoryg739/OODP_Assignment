package view.admin;

import controller.SettingController;
import view.MenuBase;

import java.util.ArrayList;

import static view.utilF.*;

public class FilterTicketSales extends MenuBase {
    SettingController sc = new SettingController();

    public FilterTicketSales(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        String variable = "sales";
        ArrayList<String> allData = sc.readSettings();
        ArrayList<String> returnData = new ArrayList<>();;
        int choice = readIntInput("Press 1 to enable Ticket Sales Display \n" +
                "Press 2 to disable Ticket Sales Display");

        if(choice == 1){ // Enable ticket sales display
            // Check if it is already enabled
            if(sc.enableTicket(variable)){
                print("Option already enabled");
            }else{
                allData.add(variable);
                print("Ticket Sales Display enabled");
                sc.writeSetting(allData);
            }

        }else if(choice == 2){ // Disable ticket sales display
            if(!sc.enableTicket(variable)){
                print("Option already disabled");
            }else{
                for(String s : allData){
                    if (!s.equals(variable)) {
                        returnData.add(s);
                    }
                }
                sc.replaceFile(returnData);
                print("Ticket Sales Display disabled");
            }
        }

        return this.getPreviousMenu();
    }



}
