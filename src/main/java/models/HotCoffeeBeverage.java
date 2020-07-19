package models;

import java.util.List;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class HotCoffeeBeverage extends Beverage {

    public HotCoffeeBeverage(List<Ingredient> Ingredients) {
        super("hot_coffee", Ingredients);
    }
}
