import models.*;

import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class BeverageFactory {

    public Beverage getBeverage(String beverage, List<Ingradient> ingradients, final Boolean allIngradientsAvailable){
        switch (beverage){
            case "hot_tea":
                return new HotTeaBeverage(beverage, ingradients){{
                    setAllIngradientsAvailable(allIngradientsAvailable);
                }};
            case "hot_coffee":
                return new HotCoffeeBeverage(beverage, ingradients){{
                    setAllIngradientsAvailable(allIngradientsAvailable);
                }};
            case "black_tea":
                return new BlackTeaBeverage(beverage, ingradients){{
                    setAllIngradientsAvailable(allIngradientsAvailable);
                }};
            case "green_tea":
                return new GreenTeaBeverage(beverage, ingradients){{
                    setAllIngradientsAvailable(allIngradientsAvailable);
                }};
            default:
                return null;
        }
    }
}
