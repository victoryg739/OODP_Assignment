import view.MainMenu;
import view.MenuBase;
import view.Quit;


/*

The start of the program
 */

public class mainProgram {
    public static void main(String[] args) {

        // Create first Main Menu
        MainMenu mm = new MainMenu(null);
        // Upcast to a MenuBase
        MenuBase nextMenu = mm;

        // Loop until a user quits the Menu
        do{
            nextMenu =  nextMenu.execute();
        }while(!(nextMenu instanceof Quit));
    }
}

