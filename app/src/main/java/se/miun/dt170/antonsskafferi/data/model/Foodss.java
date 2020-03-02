package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "foodss")
public class Foodss {

    @ElementList(inline = true)
    private ArrayList<Foods> foods;

    public ArrayList<Foods> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Foods> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "ClassPojo [foods = " + foods + "]";
    }
}
