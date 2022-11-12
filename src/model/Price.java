package model;

import java.io.Serializable;

/**
 * Represents all default price of all combinations
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class Price implements Serializable {

    private double[] price;

    /**
     * Creates an array of default prices
     */
    public Price() {
        price = new double[]{28, 38, 8.50, 9.50, 11, 11, 15, 7, 8.50, 9.50, 11, 9, 11, 15, 4, 8.50, 9.50, 11, 11, 15, 9.50, 7};
    }

    /**
     * Get price based on the index
     *
     * @return Get price based on the index.
     */
    public double getPrice(int index) {
        return price[index];
    }

    /**
     * Change the sessions of the cinema
     *
     * @param index    price based on index.
     * @param newPrice new price updated
     */
    public void setPrice(int index, double newPrice) {
        this.price[index] = newPrice;
    }


}
