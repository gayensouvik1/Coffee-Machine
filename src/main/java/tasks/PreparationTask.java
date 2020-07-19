package tasks;

import models.Beverage;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class PreparationTask implements Runnable {

    private Beverage beverage;

    public PreparationTask(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public void run() {
        System.out.println("Preparing : "+beverage.getName()+" in "+Thread.currentThread());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished : "+beverage.getName());
        beverage.setAlreadyServing(false);
    }
}
