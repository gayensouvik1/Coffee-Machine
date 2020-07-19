package models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 18/07/20
 */
public abstract class Beverage {

    protected String name;
    protected List<Ingredient> Ingredients;

    public Beverage(String name, List<Ingredient> Ingredients) {
        this.name = name;
        this.Ingredients = Ingredients;
        this.isAlreadyServing = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<Ingredient> Ingredients) {
        this.Ingredients = Ingredients;
    }

    public boolean isAlreadyServing() {
        return isAlreadyServing;
    }

    public void setAlreadyServing(boolean alreadyServing) {
        isAlreadyServing = alreadyServing;
    }

    protected boolean isAlreadyServing;

    public boolean isAllIngredientsAvailable() {
        return isAllIngredientsAvailable;
    }

    public void setAllIngredientsAvailable(boolean allIngredientsAvailable) {
        isAllIngredientsAvailable = allIngredientsAvailable;
    }

    protected boolean isAllIngredientsAvailable;


}
