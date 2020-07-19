import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Beverage;
import models.Ingradient;
import models.IngradientStorage;

import java.io.File;
import java.util.*;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class CustomDeserializer {

    public Integer deserialize(IngradientStorage ingradientStorage, List<Beverage> beverages, String file) {

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


            Map<String, Boolean> existingIngradientsInStorage = new HashMap<>();
            IngradientFactory ingradientFactory = new IngradientFactory();

            for (Map.Entry<String, Object> entry : total_items_quantity.entrySet()) {
                Ingradient ingradient = ingradientFactory.getIngradient(entry.getKey(), (Integer) entry.getValue());
                ingradientStorage.addIngradient(entry.getKey(), ingradient);
                existingIngradientsInStorage.put(entry.getKey(), true);
            }

            BeverageFactory beverageFactory = new BeverageFactory();


            for (Map.Entry<String, Object> entry : beverageMap.entrySet()) {
                LinkedHashMap<String, Object> ingradients = (LinkedHashMap<String, Object>) entry.getValue();
                List<Ingradient> ingradientList = new ArrayList<>();
                boolean allIngradientsAvailable = true;
                for (Map.Entry<String, Object> entry1 : ingradients.entrySet()) {
                    ingradientList.add(ingradientFactory.getIngradient(entry1.getKey(), (Integer) entry1.getValue()));
                    if (!existingIngradientsInStorage.containsKey(entry1.getKey())) {
                        allIngradientsAvailable = false;
                    }
                }
                Beverage beverage = beverageFactory.getBeverage(entry.getKey(), ingradientList, allIngradientsAvailable);
                beverages.add(beverage);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfOutlet;
    }

}
