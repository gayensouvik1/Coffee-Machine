package models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by souvik.gayen on 18/07/20
 */
public abstract class Beverage {

    public Beverage(String name, List<Ingradient> ingradients) {
        this.name = name;
        this.ingradients = ingradients;
        this.isAlreadyServing = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingradient> getIngradients() {
        return ingradients;
    }

    public void setIngradients(List<Ingradient> ingradients) {
        this.ingradients = ingradients;
    }

    protected String name;
    protected List<Ingradient> ingradients;

    public boolean isAlreadyServing() {
        return isAlreadyServing;
    }

    public void setAlreadyServing(boolean alreadyServing) {
        isAlreadyServing = alreadyServing;
    }

    protected boolean isAlreadyServing;

    public boolean isAllIngradientsAvailable() {
        return isAllIngradientsAvailable;
    }

    public void setAllIngradientsAvailable(boolean allIngradientsAvailable) {
        isAllIngradientsAvailable = allIngradientsAvailable;
    }

    protected boolean isAllIngradientsAvailable;


}
