/**
 * Created by souvik.gayen on 18/07/20
 */
import java.util.*;

import models.Beverage;
import models.IngredientStorage;

public class Main {

    public static void main(String args[]) {

        IngredientStorage IngredientStorage = new IngredientStorage();
        List<Beverage> beverages = new ArrayList<>();
        Integer numOfOutlet = 0;

        numOfOutlet = new CustomDeserializer().deserialize(IngredientStorage, beverages, "input.json");

        MachineManager machineManager = new MachineManager(IngredientStorage, beverages, numOfOutlet);

        machineManager.serve();

    }
}
