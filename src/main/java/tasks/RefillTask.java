package tasks;

import models.Ingredient;
import models.IngredientStorage;

import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class RefillTask implements Runnable {

    private IngredientStorage IngredientStorage;

    public RefillTask(IngredientStorage IngredientStorage) {
        this.IngredientStorage = IngredientStorage;
    }

    @Override
    public void run() {

        System.out.println("All Ingredients running low. Refilling them in "+Thread.currentThread());
        IngredientStorage.setRefillRunning(true);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String,Ingredient> entry: IngredientStorage.getIngredients().entrySet()){
            Ingredient Ingredient = entry.getValue();
            Ingredient.setQuantity(Ingredient.getMaxQuantity());
            IngredientStorage.getIngredients().put(entry.getKey(),Ingredient);
        }

        IngredientStorage.setRefillRunning(false);

        System.out.println("All Ingredients Refilled");
    }
}
