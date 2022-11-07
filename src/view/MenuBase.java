package view;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public abstract class MenuBase {
    private MenuBase initialMenu;

    public MenuBase(MenuBase initialMenu) {
        this.initialMenu = initialMenu;
    }

    protected MenuBase getPreviousMenu(){
        if(initialMenu == null){
            return this;
        }else {
            return initialMenu;
        }
    }
    // Subclass needs to implement this
    public abstract MenuBase execute() throws IOException, AddressException, MessagingException;

}
