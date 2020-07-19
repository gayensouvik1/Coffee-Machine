import models.*;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class IngredientFactory {

    public Ingredient getIngredient(String ingredient, Integer quantity){
        switch (ingredient){
            case "hot_water":
                return new HotWaterIngredient(quantity);
            case "hot_milk":
                return new HotMilkIngredient(quantity);
            case "ginger_syrup":
                return new GingerSyrupIngredient(quantity);
            case "sugar_syrup":
                return new SugarSyrupIngredient(quantity);
            case "tea_leaves_syrup":
                return new TeaLeavesSyrupIngredient(quantity);
            case "green_mixture":
                return new GreenMixtureIngredient(quantity);
                default:
                    return null;
        }
    }
}
