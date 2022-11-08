package model;

import java.io.Serializable;

/**
 * Represents a seat inside a cinema
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class Seat implements Serializable {

    private int col;
    private int row;
    private boolean taken;
    private boolean selected;
    private boolean couple;

    private boolean stairWay;

    public Seat() {
    }

    /**
     * Creates a new Cinema with multiple sessions
     *
     * @param row      This is the row of the seating plan of the cinema
     * @param col      This is the column of the seating plan of the cinema
     * @param taken    This is whether seat is taken
     * @param stairWay This is whether the seat is not avaliable and is a stariway instead
     * @param couple   This is whether the seat is a couple seat
     */
    public Seat(int col, int row, boolean taken, boolean stairWay, boolean couple) {
        this.col = col;
        this.row = row;
        this.taken = taken;
        this.selected = false;
        this.stairWay = stairWay;
        this.couple = couple;
    }

    /**
     * Gets the seat column
     *
     * @return seat column
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the seat row
     *
     * @return seat row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the whether seat is taken
     *
     * @return seat taken
     */
    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    /**
     * Gets the whether seat is taken
     *
     * @return seat taken
     */
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Gets the whether is stairway
     *
     * @return is a stairway
     */
    public boolean isStairWay() {
        return stairWay;
    }


    /**
     * Gets the whether is a couple seat
     *
     * @return is a couple seat
     */
    public boolean isCouple() {
        return couple;
    }
}
