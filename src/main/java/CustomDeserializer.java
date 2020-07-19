import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Beverage;
import models.Ingredient;
import models.IngredientStorage;

import java.io.File;
import java.util.*;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class CustomDeserializer {

    public Integer deserialize(IngredientStorage IngredientStorage, List<Beverage> beverages, String file) {

        ObjectMapper mapper = new ObjectMapper();
        Integer numOfOutlet = -1;

        try {
            Map<String, Object> data = mapper.readValue(new File(
                    System.getProperty("user.dir") + "/src/main/resources/"+file), new TypeReference<Map<String, Object>>() {
            });

            LinkedHashMap<String, Object> machine = (LinkedHashMap<String, Object>) data.get("machine");

            LinkedHashMap<String, Object> outlets = (LinkedHashMap<String, Object>) machine.get("outlets");
            LinkedHashMap<String, Object> total_items_quantity = (LinkedHashMap<String, Object>) machine.get("total_items_quantity");
            LinkedHashMap<String, Object> beverageMap = (LinkedHashMap<String, Object>) machine.get("beverages");


            numOfOutlet = (Integer) outlets.get("count_n");


            Map<String, Boolean> existingIngredientsInStorage = new HashMap<>();
            IngredientFactory IngredientFactory = new IngredientFactory();

            for (Map.Entry<String, Object> entry : total_items_quantity.entrySet()) {
                Ingredient Ingredient = IngredientFactory.getIngredient(entry.getKey(), (Integer) entry.getValue());
                IngredientStorage.addIngredient(entry.getKey(), Ingredient);
                existingIngredientsInStorage.put(entry.getKey(), true);
            }

            BeverageFactory beverageFactory = new BeverageFactory();


            for (Map.Entry<String, Object> entry : beverageMap.entrySet()) {
                LinkedHashMap<String, Object> Ingredients = (LinkedHashMap<String, Object>) entry.getValue();
                List<Ingredient> IngredientList = new ArrayList<>();
                boolean allIngredientsAvailable = true;
                for (Map.Entry<String, Object> entry1 : Ingredients.entrySet()) {
                    IngredientList.add(IngredientFactory.getIngredient(entry1.getKey(), (Integer) entry1.getValue()));
                    if (!existingIngredientsInStorage.containsKey(entry1.getKey())) {
                        allIngredientsAvailable = false;
                    }
                }
                Beverage beverage = beverageFactory.getBeverage(entry.getKey(), IngredientList, allIngredientsAvailable);
                beverages.add(beverage);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfOutlet;
    }

}
