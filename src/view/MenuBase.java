package view;

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
    public abstract MenuBase execute();

}
