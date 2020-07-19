package models;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class Machine {


    IngredientStorage IngredientStorage;
    List<Beverage> beverages;
    Integer numOfOutlet;

    public models.IngredientStorage getIngredientStorage() {
        return IngredientStorage;
    }

    public void setIngredientStorage(models.IngredientStorage ingredientStorage) {
        IngredientStorage = ingredientStorage;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public Integer getNumOfOutlet() {
        return numOfOutlet;
    }

    public void setNumOfOutlet(Integer numOfOutlet) {
        this.numOfOutlet = numOfOutlet;
    }


    public Machine(models.IngredientStorage ingredientStorage, List<Beverage> beverages, Integer numOfOutlet) {
        IngredientStorage = ingredientStorage;
        this.beverages = beverages;
        this.numOfOutlet = numOfOutlet;

    }
}
