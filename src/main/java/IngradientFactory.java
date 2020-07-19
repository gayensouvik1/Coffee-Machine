import models.*;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class IngradientFactory {

    public Ingradient getIngradient(String ingradient, Integer quantity){
        switch (ingradient){
            case "hot_water":
                return new HotWaterIngradient(ingradient, quantity);
            case "hot_milk":
                return new HotMilkIngradient(ingradient, quantity);
            case "ginger_syrup":
                return new GingerSyrupIngradient(ingradient, quantity);
            case "sugar_syrup":
                return new SugarSyrupIngradient(ingradient, quantity);
            case "tea_leaves_syrup":
                return new TeaLeavesSyrupIngradient(ingradient, quantity);
            case "green_mixture":
                return new GreenMixtureIngradient(ingradient, quantity);
                default:
                    return null;
        }
    }
}
