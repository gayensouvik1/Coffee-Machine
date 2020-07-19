package models;

/**
 * Created by souvik.gayen on 18/07/20
 */
public abstract class Ingredient {

    protected Ingredient(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
        this.maxQuantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    protected String name;
    protected Integer quantity;

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    protected Integer maxQuantity;

}
