package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class IngredientStorage {

    private Map<String, Ingredient> Ingredients;
    private Boolean refillRunning;

    public IngredientStorage() {
        Ingredients = new HashMap<>();
        refillRunning = false;
    }

    public Boolean isRefillRunning() {
        return refillRunning;
    }

    public void setRefillRunning(Boolean refillRunning) {
        this.refillRunning = refillRunning;
    }

    public Map<String, Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(Map<String,Ingredient> Ingredients) {
        this.Ingredients = Ingredients;
    }

    public void addIngredient(String name, Ingredient Ingredient) {
        this.Ingredients.put(name, Ingredient);
    }


}
