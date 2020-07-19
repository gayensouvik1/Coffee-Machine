package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 19/07/20
 */
public class IngradientStorage {

    private Map<String, Ingradient> ingradients;

    public Boolean getRefillRunning() {
        return isRefillRunning;
    }

    public void setRefillRunning(Boolean refillRunning) {
        isRefillRunning = refillRunning;
    }

    private Boolean isRefillRunning;

    public IngradientStorage() {
        ingradients = new HashMap<>();
        isRefillRunning = false;
    }

    public Map<String, Ingradient> getIngradients() {
        return ingradients;
    }

    public void setIngradients(Map<String,Ingradient> ingradients) {
        this.ingradients = ingradients;
    }

    public void addIngradient(String name, Ingradient ingradient) {
        this.ingradients.put(name, ingradient);
    }


}
