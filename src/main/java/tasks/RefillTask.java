package tasks;

import models.Ingradient;
import models.IngradientStorage;

import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class RefillTask implements Runnable {

    private IngradientStorage ingradientStorage;

    public RefillTask(IngradientStorage ingradientStorage) {
        this.ingradientStorage = ingradientStorage;
    }

    @Override
    public void run() {

        System.out.println("All Ingradients running low. Refilling them in "+Thread.currentThread());
        ingradientStorage.setRefillRunning(true);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String,Ingradient> entry: ingradientStorage.getIngradients().entrySet()){
            Ingradient ingradient = entry.getValue();
            ingradient.setQuantity(ingradient.getMaxQuantity());
            ingradientStorage.getIngradients().put(entry.getKey(),ingradient);
        }

        ingradientStorage.setRefillRunning(false);

        System.out.println("All Ingradients Refilled");
    }
}
