import view.MainMenu;
import view.MenuBase;
import view.Quit;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * The Entry of the program
 * Creates the default settings, movie, cinema and an admin account
 * Creates a menu to execute untl the nextMenu becomes quit
 * @author Bryan Tay Peng Keat
 * @version 1.0
 * @since 2022-08-11
 */
public class MainProgram {

    /**
     * Entry Program
     */
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

