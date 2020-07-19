import models.Beverage;
import models.Ingradient;
import models.IngradientStorage;
import tasks.PreparationTask;
import tasks.RefillTask;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by souvik.gayen on 18/07/20
 */
public class MachineManager {

    IngradientStorage ingradientStorage;
    List<Beverage> beverages;
    Integer numOfOutlet;
    ExecutorService outlet, refill;

    public MachineManager(IngradientStorage ingradientStorage, List<Beverage> beverages, Integer numOfOutlet){
        this.ingradientStorage = ingradientStorage;
        this.beverages = beverages;
        this.numOfOutlet = numOfOutlet;
        outlet = Executors.newFixedThreadPool(numOfOutlet);
        refill = Executors.newFixedThreadPool(1);
    }


    public void serve() {
        while(true){
            serveNow();
        }
    }

    private void serveNow(){
        Beverage beverage = getSuitableBeverage();
        if(beverage!=null){
            Runnable preparationTask = new PreparationTask(beverage);
            outlet.execute(preparationTask);
        }else if(!ingradientStorage.getRefillRunning() && isRefillRequired()){

            Runnable refillTask = new RefillTask(ingradientStorage);
            refill.execute(refillTask);
        }
    }

    private boolean isRefillRequired() {
        Map<String,Ingradient> allCurrentIngradients = ingradientStorage.getIngradients();
        for(Beverage beverage: beverages){
            Boolean canThisBeverageBePrepared = true;
            for(Ingradient ingradient: beverage.getIngradients()){
                if(allCurrentIngradients.get(ingradient.getName()).getQuantity() < ingradient.getQuantity()){
                    canThisBeverageBePrepared = false;
                    break;
                }
            }

            if(canThisBeverageBePrepared)
                return false;
        }
        return true;
    }

    private synchronized Beverage getSuitableBeverage() {

        Collections.shuffle(beverages);

        int numOfBeverages = beverages.size();

        for(int i=0; i<numOfBeverages; i++){
            Beverage beverage = beverages.get(i);
            if(!beverage.isAlreadyServing() && beverage.isAllIngradientsAvailable()){
                Map<String, Ingradient> ingradientMap = ingradientStorage.getIngradients();
                boolean canBeSelected = true;
                for(Ingradient ingradient:beverage.getIngradients()){
                    if(ingradient.getQuantity() > ingradientMap.get(ingradient.getName()).getQuantity()){
                        canBeSelected = false;
                        break;
                    }
                }

                if(canBeSelected){
                    for(Ingradient ingradient:beverage.getIngradients()){
                        Ingradient currentIngradient = ingradientMap.get(ingradient.getName());
                        currentIngradient.setQuantity(currentIngradient.getQuantity() - ingradient.getQuantity());
                        ingradientMap.put(ingradient.getName(), currentIngradient);
                    }
                    beverage.setAlreadyServing(true);
                    return beverages.get(i);
                }

            }
        }
        return null;
    }
}
