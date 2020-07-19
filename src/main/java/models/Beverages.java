package models;

import java.util.List;

/**
 * Created by souvik.gayen on 18/07/20
 */
public class Beverages {

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public Beverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    List<Beverage> beverages;

}
