import models.Beverage;
import models.Ingredient;
import models.IngredientStorage;
import models.Machine;
import tasks.PreparationTask;
import tasks.RefillTask;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by souvik.gayen on 18/07/20
 */
public class MachineManager {

    Machine machine;
    ExecutorService outlet, refill;

    public MachineManager(IngredientStorage ingredientStorage, List<Beverage> beverages, Integer numOfOutlet){
        this.machine = new Machine(ingredientStorage, beverages, numOfOutlet);
        outlet = Executors.newFixedThreadPool(numOfOutlet);
        refill = Executors.newFixedThreadPool(1);
    }


    public void serve() {
        if(hasNotEnoughCapacity()){
            System.out.println("Coffee machine doesn't have enough capacity");
            return;
        }
        while(true){
            serveNow();
        }
    }

    private void serveNow(){
        Beverage beverage = getNextBeverageToPrepare();
        if(beverage!=null){
            System.out.println("Going to Prepare next : "+beverage.getName());
            Runnable preparationTask = new PreparationTask(beverage);
            outlet.execute(preparationTask);
        }else if(!machine.getIngredientStorage().isRefillRunning() && isRefillRequired()){
            Runnable refillTask = new RefillTask(machine.getIngredientStorage());
            refill.execute(refillTask);
        }
    }

    private boolean hasNotEnoughCapacity(){
        Map<String,Ingredient> allCurrentIngredients = machine.getIngredientStorage().getIngredients();
        for(Beverage beverage: machine.getBeverages()){
            Boolean canThisBeverageBePrepared = true;
            for(Ingredient Ingredient: beverage.getIngredients()){
                if(allCurrentIngredients.get(Ingredient.getName()).getMaxQuantity() < Ingredient.getQuantity()){
                    canThisBeverageBePrepared = false;
                    break;
                }
            }

            if(canThisBeverageBePrepared)
                return false;
        }
        return true;
    }

    private boolean isRefillRequired() {
        Map<String,Ingredient> allCurrentIngredients = machine.getIngredientStorage().getIngredients();
        for(Beverage beverage: machine.getBeverages()){
            Boolean canThisBeverageBePrepared = true;
            for(Ingredient Ingredient: beverage.getIngredients()){
                if(allCurrentIngredients.get(Ingredient.getName()).getQuantity() < Ingredient.getQuantity()){
                    canThisBeverageBePrepared = false;
                    break;
                }
            }

            if(canThisBeverageBePrepared)
                return false;
        }
        return true;
    }

    private synchronized Beverage getNextBeverageToPrepare() {

        List<Beverage> beverages = machine.getBeverages();

        Collections.shuffle(beverages);

        int numOfBeverages = beverages.size();

        for(int i=0; i<numOfBeverages; i++){
            Beverage beverage = beverages.get(i);
            if(!beverage.isAlreadyServing() && beverage.isAllIngredientsAvailable()){
                Map<String, Ingredient> IngredientMap = machine.getIngredientStorage().getIngredients();
                boolean canBeSelected = true;
                for(Ingredient Ingredient:beverage.getIngredients()){
                    if(Ingredient.getQuantity() > IngredientMap.get(Ingredient.getName()).getQuantity()){
                        canBeSelected = false;
                        break;
                    }
                }

                if(canBeSelected){
                    for(Ingredient Ingredient:beverage.getIngredients()){
                        Ingredient currentIngredient = IngredientMap.get(Ingredient.getName());
                        currentIngredient.setQuantity(currentIngredient.getQuantity() - Ingredient.getQuantity());
                        IngredientMap.put(Ingredient.getName(), currentIngredient);
                    }
                    beverage.setAlreadyServing(true);
                    return beverages.get(i);
                }

            }
        }
        return null;
    }
}
