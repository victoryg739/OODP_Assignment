package modal;

import java.io.Serializable;

public class Price implements Serializable {

    private double[] price;

    public Price(){
       price = new double[]{28,38,8.50,9.50,11,11,15,7,8.50,9.50,11,9,11,15,4,8.50,9.50,11,11,15,9.50,7};
    }

    public double getPrice(int index) {
        return price[index];
    }

    public void setPrice(int index, double newPrice) {
        this.price[index] = newPrice;
    }



}
