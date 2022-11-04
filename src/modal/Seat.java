package modal;

import java.io.Serializable;

public class Seat implements Serializable {

    private int col;
    private int row;
    private boolean taken,selected;
    public Seat(int col, int row, boolean taken) {
        this.col = col;
        this.row = row;
        this.taken = taken;
        this.selected = false;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
