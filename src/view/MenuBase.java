package view;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;


/**
 * The Base class for ALL menus
 * @author Bryan Tay Peng Keat
 * @version 1.0
 * @since 2022-08-11
 */

public abstract class MenuBase {
    private MenuBase initialMenu;

    /**
     * This method set intialMenu
     *
     */
    public MenuBase(MenuBase initialMenu) {
        this.initialMenu = initialMenu;
    }

    /**
     * This method invokes previous menu page if is not null
     *
     * @return previous menu
     */
    protected MenuBase getPreviousMenu() {
        if (initialMenu == null) {
            return this;
        } else {
            return initialMenu;
        }
    }

    /**
     * This method will be invoked by every menu
     *
     * @return next menu to be displayed
     */
    public abstract MenuBase execute() throws IOException, AddressException, MessagingException;

}
