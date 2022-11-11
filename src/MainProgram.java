import view.MainMenu;
import view.MenuBase;
import view.Quit;

import javax.mail.MessagingException;
import java.io.IOException;

/*

The start of the program
 */

public class MainProgram {

    public static void main(String[] args) throws IOException, MessagingException {
        /* For Testing Purposes */
        Tester tester = new Tester();
        tester.createDefaultSettings();
        tester.createDefaultMovie();
        tester.createCineplexCinema();
        tester.createDefaultAccount();


        MenuBase nextMenu = new MainMenu(null);

        do {
            nextMenu = nextMenu.execute();

        } while (!(nextMenu instanceof Quit));
    }
}

