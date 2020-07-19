/**
 * Created by souvik.gayen on 18/07/20
 */
import java.io.File;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Beverage;
import models.Beverages;
import models.Ingradient;
import models.IngradientStorage;

public class Main {

    public static void main(String args[]) {

        IngradientStorage ingradientStorage = new IngradientStorage();
        List<Beverage> beverages = new ArrayList<>();
        Integer numOfOutlet = 0;

        numOfOutlet = new CustomDeserializer().deserialize(ingradientStorage, beverages, "input2.json");

        MachineManager machineManager = new MachineManager(ingradientStorage, beverages, numOfOutlet);

        machineManager.serve();

    }
}
