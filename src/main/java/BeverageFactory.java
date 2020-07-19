import models.*;

import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class BeverageFactory {

    public Beverage getBeverage(String beverage, List<Ingredient> Ingredients, final Boolean allIngredientsAvailable){
        switch (beverage){
            case "hot_tea":
                return new HotTeaBeverage(Ingredients){{
                    setAllIngredientsAvailable(allIngredientsAvailable);
                }};
            case "hot_coffee":
                return new HotCoffeeBeverage(Ingredients){{
                    setAllIngredientsAvailable(allIngredientsAvailable);
                }};
            case "black_tea":
                return new BlackTeaBeverage(Ingredients){{
                    setAllIngredientsAvailable(allIngredientsAvailable);
                }};
            case "green_tea":
                return new GreenTeaBeverage(Ingredients){{
                    setAllIngredientsAvailable(allIngredientsAvailable);
                }};
            default:
                return null;
        }
    }
}
